var Frame = function() {

    var handleConHeight = function() {
        var viewHeight = $(document).height(); //窗口可视高度  
        var headerHeight = $('.header').outerHeight(true);
        var height = viewHeight - headerHeight;
        $('.page-container').css('height', height);
    }
    return {
        init: function() {

            handleConHeight();
            window.onresize = handleConHeight;
        }
    }
}()
