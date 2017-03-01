var Frame = function() {

    var setFrameSize = function() {
        if ($(document).width() <= 991) {
            $('#mainFrame').css({
                'width': $(document).width(),
                'height': $(document).height() - $('#header').height(),
            });
        } else {
            $('#mainFrame').css({
                'width': $(document).width() - $('#sidebar').width(),
                'height': $(document).height() - $('#header').height(),
            });
        }
    }

    var resizeChart = function() {
        $("#mainFrame")[0].contentWindow.Main.fnBody();
    }

    var handleSidebarToggle = function() {
        $('.sidebar-toggler').bind('click', function() {
            if ($('body').hasClass("page-sidebar-hover-on")) {  // for page-sidebar-fixed
                $('#mainFrame').css({
                    'width': $(document).width() - 225,
                })
            } else {                
                var width = $('#sidebar').outerWidth() == 225 ? $(document).width() - 35 : $(document).width() - 225;
                $('#mainFrame').css({
                    'width': width,
                })
            }
            resizeChart();
        });
    }

    return {

        //main function to initiate the page 
        init: function() {
            setFrameSize();
            window.onresize = function() {
                setFrameSize();
                resizeChart();
            };
            handleSidebarToggle();
        }

    }

}();
