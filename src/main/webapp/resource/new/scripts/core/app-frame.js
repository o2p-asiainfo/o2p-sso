/**
Core script to handle the entire theme and core functions
**/
var App = function() {
    // IE mode
    var isRTL = false;
    var isIE8 = false;
    var isIE9 = false;
    var isIE10 = false;
    var sidebarWidth = 225;
    var sidebarCollapsedWidth = 35;
    var responsiveHandlers = [];
    
    // To get the correct viewport width based on  http://andylangton.co.uk/articles/javascript/get-viewport-size-javascript/
    var _getViewPort = function() {
            var e = window,
                a = 'inner';
            if (!('innerWidth' in window)) {
                a = 'client';
                e = document.documentElement || document.body;
            }
            return {
                width: e[a + 'Width'],
                height: e[a + 'Height']
            }
        }
       
    var handleSidebarState = function() {
            // remove sidebar toggler if window width smaller than 992(for tablet and phone mode)
            var viewport = _getViewPort();
            if (viewport.width < 992) {
                $('body').removeClass("page-sidebar-closed");
            }
        }
        // runs callback functions set by App.addResponsiveHandler().
    var runResponsiveHandlers = function() {
            // reinitialize other subscribed elements
            for (var i = 0; i < responsiveHandlers.length; i++) {
                var each = responsiveHandlers[i];
                each.call();
            }
        }
        // reinitialize the laypot on window resize
    var handleResponsive = function() {
            handleSidebarState();
            handleSidebarAndContentHeight();
            handleFixedSidebar();
            runResponsiveHandlers();
        }
        // initialize the layout on page load
    var handleResponsiveOnInit = function() {
            handleSidebarState();
            handleSidebarAndContentHeight();
        }
        // handle the layout reinitialization on window resize
    var handleResponsiveOnResize = function() {
            var resize;
            if (isIE8) {
                var currheight;
                $(window).resize(function() {
                    if (currheight == document.documentElement.clientHeight) {
                        return; //quite event since only body resized not window.
                    }
                    if (resize) {
                        clearTimeout(resize);
                    }
                    resize = setTimeout(function() {
                        handleResponsive();
                    }, 50); // wait 50ms until window resize finishes.                
                    currheight = document.documentElement.clientHeight; // store last body client height
                });
            } else {
                $(window).resize(function() {
                    if (resize) {
                        clearTimeout(resize);
                    }
                    resize = setTimeout(function() {
                        handleResponsive();
                    }, 50); // wait 50ms until window resize finishes.
                });
            }
        }
        //* BEGIN:CORE HANDLERS *//
        // this function handles responsive layout on screen size resize or mobile device rotate.
        // Set proper height for sidebar and content. The content and sidebar height must be synced always.
    var handleSidebarAndContentHeight = function() {
            var content = $('.page-content');
            var sidebar = $('.page-sidebar');
            var body = $('body');
            var height;
            if (body.hasClass("page-footer-fixed") === true && body.hasClass("page-sidebar-fixed") === false) {
                var available_height = $(document).height() - $('.footer').outerHeight() - $('.header').outerHeight();
                if (content.height() < available_height) {
                    content.attr('style', 'min-height:' + available_height + 'px !important');
                }
            } else {
                if (body.hasClass('page-sidebar-fixed')) {
                    height = _calculateFixedSidebarViewportHeight();
                } else {
                    height = sidebar.height() + 20;
                    var headerHeight = $('.header').outerHeight();
                    var footerHeight = $('.footer').outerHeight();
                    if ($(document).width() > 1024 && (height + headerHeight + footerHeight) < $(document).height()) {
                        height = $(document).height() - headerHeight - footerHeight;
                    }
                }
                if (height >= content.height()) {
                    content.attr('style', 'min-height:' + height + 'px !important');
                }
            }
        }
        // Handle sidebar menu
    var handleSidebarMenu = function() {
            jQuery('.page-sidebar').on('click', 'li > a', function(e) {
                if ($(this).next().hasClass('sub-menu') == false) {
                    if ($('.btn-navbar').hasClass('collapsed') == false) {
                        $('.btn-navbar').click();
                    }
                    return;
                }
                if ($(this).next().hasClass('sub-menu always-open')) {
                    return;
                }
                var parent = $(this).parent().parent();
                var the = $(this);
                var menu = $('.page-sidebar-menu');
                var sub = jQuery(this).next();
                var autoScroll = menu.data("auto-scroll") ? menu.data("auto-scroll") : true;
                var slideSpeed = menu.data("slide-speed") ? parseInt(menu.data("slide-speed")) : 200;
                parent.children('li.open').children('a').children('.arrow').removeClass('open');
                parent.children('li.open').children('.sub-menu:not(.always-open)').slideUp(200);
                parent.children('li.open').removeClass('open');
                var slideOffeset = -200;
                if (sub.is(":visible")) {
                    jQuery('.arrow', jQuery(this)).removeClass("open");
                    jQuery(this).parent().removeClass("open");
                    sub.slideUp(slideSpeed, function() {
                        if (autoScroll == true && $('body').hasClass('page-sidebar-closed') == false) {
                            if ($('body').hasClass('page-sidebar-fixed')) {
                                menu.slimScroll({
                                    'scrollTo': (the.position()).top
                                });
                            } else {
                                App.scrollTo(the, slideOffeset);
                            }
                        }
                        handleSidebarAndContentHeight();
                    });
                } else {
                    jQuery('.arrow', jQuery(this)).addClass("open");
                    jQuery(this).parent().addClass("open");
                    sub.slideDown(slideSpeed, function() {
                        if (autoScroll == true && $('body').hasClass('page-sidebar-closed') == false) {
                            if ($('body').hasClass('page-sidebar-fixed')) {
                                menu.slimScroll({
                                    'scrollTo': (the.position()).top
                                });
                            } else {
                                App.scrollTo(the, slideOffeset);
                            }
                        }
                        handleSidebarAndContentHeight();
                    });
                }
                e.preventDefault();
            });
            // handle ajax links within sidebar menu
            jQuery('.page-sidebar').on('click', ' li > a.ajaxify', function(e) {
                e.preventDefault();
                App.scrollTop();
                var url = $(this).attr("href");
                var menuContainer = jQuery('.page-sidebar ul');
                var pageContent = $('.page-content');
                var pageContentBody = $('.page-content .page-content-body');
                menuContainer.children('li.active').removeClass('active');
                menuContainer.children('arrow.open').removeClass('open');
                $(this).parents('li').each(function() {
                    $(this).addClass('active');
                    $(this).children('a > span.arrow').addClass('open');
                });
                $(this).parents('li').addClass('active');
                App.startPageLoading();
                if ($(document).width() <= 991 && $('.page-sidebar').hasClass("in")) {
                    $('.navbar-toggle').click();
                }
                $.ajax({
                    type: "GET",
                    cache: false,
                    url: url,
                    dataType: "html",
                    success: function(res) {
                        App.stopPageLoading();
                        pageContentBody.html(res);
                        App.fixContentHeight(); // fix content height
                        App.initAjax(); // initialize core stuff
                    },
                    error: function(xhr, ajaxOptions, thrownError) {
                        pageContentBody.html('<h4>Could not load the requested content.</h4>');
                        App.stopPageLoading();
                    }
                });
            });
            // handle ajax link within main content
            jQuery('.page-content').on('click', '.ajaxify', function(e) {
                e.preventDefault();
                App.scrollTop();
                var url = $(this).attr("href");
                var pageContent = $('.page-content');
                var pageContentBody = $('.page-content .page-content-body');
                App.startPageLoading();
                if ($(document).width() <= 991 && $('.page-sidebar').hasClass("in")) {
                    $('.navbar-toggle').click();
                }
                $.ajax({
                    type: "GET",
                    cache: false,
                    url: url,
                    dataType: "html",
                    success: function(res) {
                        App.stopPageLoading();
                        pageContentBody.html(res);
                        App.fixContentHeight(); // fix content height
                        App.initAjax(); // initialize core stuff
                    },
                    error: function(xhr, ajaxOptions, thrownError) {
                        pageContentBody.html('<h4>Could not load the requested content.</h4>');
                        App.stopPageLoading();
                    }
                });
            });
        }
        // Helper function to calculate sidebar height for fixed sidebar layout.
    var _calculateFixedSidebarViewportHeight = function() {
            var sidebarHeight = $(document).height() - $('.header').height() + 1;
            if ($('body').hasClass("page-footer-fixed")) {
                sidebarHeight = sidebarHeight - $('.footer').outerHeight();
            }
            return sidebarHeight;
        }
        // Handles fixed sidebar
    var handleFixedSidebar = function() {
            var menu = $('.page-sidebar-menu');
            if (menu.parent('.slimScrollDiv').size() === 1) { // destroy existing instance before updating the height
                menu.slimScroll({
                    destroy: true
                });
                menu.removeAttr('style');
                $('.page-sidebar').removeAttr('style');
            }
            if ($('.page-sidebar-fixed').size() === 0) {
                handleSidebarAndContentHeight();
                return;
            }
            var viewport = _getViewPort();
            if (viewport.width >= 992) {
                var sidebarHeight = _calculateFixedSidebarViewportHeight();
                menu.slimScroll({
                    size: '7px',
                    color: '#a1b2bd',
                    opacity: .3,
                    position: isRTL ? 'left' : 'right',
                    height: sidebarHeight,
                    allowPageScroll: false,
                    disableFadeOut: false
                });
                handleSidebarAndContentHeight();
            }
        }
        // Handles the sidebar menu hover effect for fixed sidebar.
    var handleFixedSidebarHoverable = function() {
            if ($('body').hasClass('page-sidebar-fixed') === false) {
                return;
            }
            $('.page-sidebar').off('mouseenter').on('mouseenter', function() {
                var body = $('body');
                if ((body.hasClass('page-sidebar-closed') === false || body.hasClass('page-sidebar-fixed') === false) || $(this).hasClass('page-sidebar-hovering')) {
                    return;
                }
                body.removeClass('page-sidebar-closed').addClass('page-sidebar-hover-on');
                if (body.hasClass("page-sidebar-reversed")) {
                    $(this).width(sidebarWidth);
                } else {
                    $(this).addClass('page-sidebar-hovering');
                    $(this).animate({
                        width: sidebarWidth
                    }, 400, '', function() {
                        $(this).removeClass('page-sidebar-hovering');
                    });
                }
            });
            $('.page-sidebar').off('mouseleave').on('mouseleave', function() {
                var body = $('body');
                if ((body.hasClass('page-sidebar-hover-on') === false || body.hasClass('page-sidebar-fixed') === false) || $(this).hasClass('page-sidebar-hovering')) {
                    return;
                }
                if (body.hasClass("page-sidebar-reversed")) {
                    $('body').addClass('page-sidebar-closed').removeClass('page-sidebar-hover-on');
                    $(this).width(sidebarCollapsedWidth);
                } else {
                    $(this).addClass('page-sidebar-hovering');
                    $(this).animate({
                        width: sidebarCollapsedWidth
                    }, 400, '', function() {
                        $('body').addClass('page-sidebar-closed').removeClass('page-sidebar-hover-on');
                        $(this).removeClass('page-sidebar-hovering');
                    });
                }
            });
        }
        // Handles sidebar toggler to close/hide the sidebar.
    var handleSidebarToggler = function() {
            var viewport = _getViewPort();
            if ($.cookie && $.cookie('sidebar_closed') === '1' && viewport.width >= 992) {
                $('body').addClass('page-sidebar-closed');
            }
            // handle sidebar show/hide
            $('.page-sidebar, .header').on('click', '.sidebar-toggler', function(e) {
                var body = $('body');
                var sidebar = $('.page-sidebar');
                if ((body.hasClass("page-sidebar-hover-on") && body.hasClass('page-sidebar-fixed')) || sidebar.hasClass('page-sidebar-hovering')) {
                    body.removeClass('page-sidebar-hover-on');
                    sidebar.css('width', '').hide().show();
                    handleSidebarAndContentHeight(); //fix content & sidebar height
                    if ($.cookie) {
                        $.cookie('sidebar_closed', '0');
                    }
                    e.stopPropagation();
                    runResponsiveHandlers();
                    return;
                }
                $(".sidebar-search", sidebar).removeClass("open");
                if (body.hasClass("page-sidebar-closed")) {
                    body.removeClass("page-sidebar-closed");
                    if (body.hasClass('page-sidebar-fixed')) {
                        sidebar.css('width', '');
                    }
                    if ($.cookie) {
                        $.cookie('sidebar_closed', '0');
                    }
                } else {
                    body.addClass("page-sidebar-closed");
                    if ($.cookie) {
                        $.cookie('sidebar_closed', '1');
                    }
                }
                handleSidebarAndContentHeight(); //fix content & sidebar height
                runResponsiveHandlers();
            });
            // handle the search bar close
            $('.page-sidebar').on('click', '.sidebar-search .remove', function(e) {
                e.preventDefault();
                $('.sidebar-search').removeClass("open");
            });
            // handle the search query submit on enter press
            $('.page-sidebar .sidebar-search').on('keypress', 'input.form-control', function(e) {
                if (e.which == 13) {
                    $('.sidebar-search').submit();
                    return false; //<---- Add this line
                }
            });
            // handle the search submit(for sidebar search and responsive mode of the header search)
            $('.sidebar-search .submit').on('click', function(e) {
                e.preventDefault();
                if ($('body').hasClass("page-sidebar-closed")) {
                    if ($('.sidebar-search').hasClass('open') == false) {
                        if ($('.page-sidebar-fixed').size() === 1) {
                            $('.page-sidebar .sidebar-toggler').click(); //trigger sidebar toggle button
                        }
                        $('.sidebar-search').addClass("open");
                    } else {
                        $('.sidebar-search').submit();
                    }
                } else {
                    $('.sidebar-search').submit();
                }
            });
            // header search box:
            // handle the search query submit on enter press
            $('.header .search-form').on('keypress', 'input.form-control', function(e) {
                if (e.which == 13) {
                    $('.sidebar-search').submit();
                    return false; //<---- Add this line
                }
            });
            //handle header search button click
            $('.header .search-form .submit').on('click', function(e) {
                e.preventDefault();
                $('.header .search-form').submit();
            });
        }
        // Handles the horizontal menu
    var handleHorizontalMenu = function() {
            //handle hor menu search form toggler click
            $('.header').on('click', '.hor-menu .hor-menu-search-form-toggler', function(e) {
                if ($(this).hasClass('off')) {
                    $(this).removeClass('off');
                    $('.header .hor-menu .search-form').hide();
                } else {
                    $(this).addClass('off');
                    $('.header .hor-menu .search-form').show();
                }
                e.preventDefault();
            });
            //handle tab click
            $('.header').on('click', '.hor-menu a[data-toggle="tab"]', function(e) {
                e.preventDefault();
                var nav = $(".hor-menu .nav");
                var active_link = nav.find('li.current');
                $('li.active', active_link).removeClass("active");
                $('.selected', active_link).remove();
                var new_link = $(this).parents('li').last();
                new_link.addClass("current");
                new_link.find("a:first").append('<span class="selected"></span>');
            });
            //handle hor menu search button click
            $('.header').on('click', '.hor-menu .search-form .btn', function(e) {
                $('.form-search').submit();
                e.preventDefault();
            });
            //handle hor menu search form on enter press
            $('.header').on('keypress', '.hor-menu .search-form input', function(e) {
                if (e.which == 13) {
                    $('.form-search').submit();
                    return false;
                }
            });
        }
      
       
    var handleBootstrapSwitch = function() {
            if (!jQuery().bootstrapSwitch) {
                return;
            }
            $('.make-switch').bootstrapSwitch();
        }
    
        // Handle Hower Dropdowns
    var handleDropdownHover = function() {
        $('[data-hover="dropdown"]').dropdownHover();
    }
    
    var handleScrollers = function() {
            $('.scroller').each(function() {
                var height;
                if ($(this).attr("data-height")) {
                    height = $(this).attr("data-height");
                } else {
                    height = $(this).css('height');
                }
                $(this).slimScroll({
                    allowPageScroll: true,
                    // allow page scroll when the element scroll is ended
                    size: '7px',
                    color: ($(this).attr("data-handle-color") ? $(this).attr("data-handle-color") : '#bbb'),
                    railColor: ($(this).attr("data-rail-color") ? $(this).attr("data-rail-color") : '#eaeaea'),
                    position: isRTL ? 'left' : 'right',
                    height: height,
                    // alwaysVisible: ($(this).attr("data-always-visible") == "1" ? true : false),
                    alwaysVisible: true,
                    railVisible: ($(this).attr("data-rail-visible") == "1" ? true : false),
                    disableFadeOut: true
                });
            });
        }    
    
        //* END:CORE HANDLERS *//
    return {              
        //main function to initiate the theme
        init: function() {
            
            //layout handlers
            handleFixedSidebar(); // handles fixed sidebar menu
            handleFixedSidebarHoverable(); // handles fixed sidebar on hover effect 
            handleSidebarMenu(); // handles main menu            
            handleSidebarToggler(); // handles sidebar hide/show            
            handleResponsiveOnResize();
           
        },
       
        // wrapper function to scroll(focus) to an element
        scrollTo: function(el, offeset) {
            var pos = (el && el.size() > 0) ? el.offset().top : 0;
            if (el) {
                if ($('body').hasClass('page-header-fixed')) {
                    pos = pos - $('.header').height();
                }
                pos = pos + (offeset ? offeset : -1 * el.height());
            }
            jQuery('html,body').animate({
                scrollTop: pos
            }, 'slow');
        },
        // function to scroll to the top
        scrollTop: function() {
            App.scrollTo();
        },
       
        
      
        
    };
}();