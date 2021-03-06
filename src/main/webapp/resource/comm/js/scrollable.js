scrollable = function(content, render, options, beforeScroll) {                                            
    /*                                                                                                     
     * @author: selfimpr                                                                                   
     * @blog: http://blog.csdn.net/lgg201                                                                  
     * @e-mail: lgg860911@yahoo.com.cn                                                                     
     *                                                                                                     
     * 注意:                                                                                               
     *      1. content必须自己指定宽度. 如果其中的元素使用块元素, 请使用float: left向左浮动.               
     *      2. 使用时, 尽量自定义样式, 由于本人水平欠佳, 不能作出更加通用的东西, 呵呵.                     
     *                                                                                                     
     * 参数解释                                                                                            
     * content: 内容元素, 可以是选择器或JQUERY封装的DOM元素                                                
     * render: 渲染到的目标容器, 可以是选择器或JQUERY封装的DOM元素                                         
     * options: 选项                                                                                       
     *      scrollable_class: 整体scrollable的外框架样式 , 默认: ui-scrollable                             
     *      scrollable_left_class: 左按钮的样式, 默认: ui-scrollable-left                                  
     *      scrollable_container_class: 内容容器的样式, 默认: ui-scrollable-container                      
     *      scrollable_right_class: 右按钮的样式, 默认: ui-scrollable-right                                
     *      delay: 鼠标放上或点击按钮时两次移动之间的时间间隔, 整数                                        
     *      speed: 鼠标放上按钮时, 一次移动的距离, 整数                                                    
     *      speedup: 鼠标点下按钮时, 一次移动的距离, 整数                                                  
     *      resizeEvent: 是否监听窗口改变大小的事件, 布尔值,                                               
     *          监听窗口改变大小时, 在刷新页面后, 感觉显示有点别扭, 所以默认了false                        
     * beforeScroll: 内容滚动时候的事件回调方法.                                                           
     *      接受参数(两个对象): 第一个是滚动前内容左右位置, 第二个是滚动后内容左右位置.                    
     *      注意: 该事件可以使内容不受边界限制的滚动.                                                      
     */                                                                                                    
    options.scrollable_class = options.scrollable_class || 'ui-scrollable';                                
    options.scrollable_left_class = options.scrollable_left_class || 'ui-scrollable-left';                 
    options.scrollable_container_class = options.scrollable_container_class || 'ui-scrollable-container';  
    options.scrollable_right_class = options.scrollable_right_class || 'ui-scrollable-right';              
    options.leftText = options.leftText || '';                                                             
    options.rightText = options.rightText || '';                                                           
    options.delay = options.delay || 50;                                                                   
    options.speed = options.speed || 5;                                                                    
    options.speedup = options.speedup || 30;                                                               
    options.resizeEvent = options.resizeEvent || false;
    options.buttonClass =options.buttonClass || '.button_center';
    //options.onButtonClick =  options.onButtonClick;                                              
                                                                                                           
    var render = (typeof render == 'string' ? $(render) : render);                                         
    var content = (typeof content == 'string' ? $(content) : content);                                     
    var scrollable = $('<div></div>')                                                                      
                    .attr('id', 'scrollable_' + content.attr('id'))                                        
                    .attr('class', options.scrollable_class);                                          
                                                                                                           
    var left = $('<div></div>')                                                                            
                .attr('id', 'scrollable_left_' + content.attr('id'))                                       
                .attr('class', options.scrollable_left_class); 
                                                  
    left.text(options.leftText);                                                                           
                                                                                                           
    var container = $('<div></div>')                                                                       
                    .attr('id', 'scrollable_container_' + content.attr('id'))                             
                    .attr('class', options.scrollable_container_class);                                
                                                                                                       
    content.css('line-height', '28px')                                                                     
            .css('position', 'relative')                                                                   
            .css('left', '0px')                                                                            
            .css('overflow', 'hidden')                                                                     
            .css('float', 'left');                                                                         
    
                                                                                                     
    var right = $('<div></div>')                                                                           
                .attr('id', 'scrollable_right_' + content.attr('id'))                                      
                .attr('class', options.scrollable_right_class);                                        
    right.text(options.rightText);                                                                         
                                                                                                           
    show = function() {                                                                                    
        scrollable.appendTo(render);                                                                       
        container.appendTo(scrollable);                                                                    
        left.css('display', '');                                                                           
        right.css('display', '');                                                                          
        content.appendTo(container);                                                                       
        left.prependTo(scrollable);                                                                        
        right.appendTo(scrollable);                                                                        
        if(content.width() <= container.width() + 20) {                                                    
            scrollable.remove('.' + options.scrollable_left_class);                                        
            scrollable.remove('.' + options.scrollable_right_class);                                       
            left.css('display', 'none');                                                                   
            right.css('display', 'none');                                                                  
            container.width(content.width());                                                              
            scrollable.width(container.width());                                                           
        }                                                                                                  
        container.position = {left: container.css('left').substr(0, -2)}                                   
        container.position.right = container.position.left + container.width();                            
        content.position = {left: new Number(content.css('left').substr(0, -2))}                           
        content.position.right = content.position.left + content.width();                                  
    };                                                                                                     
                                                                                                           
    show();  
    
    var onButtonClick =function(currButton){
    	$(options.buttonClass).removeClass("on");
    	$(this).addClass("on");
    	
    }
    $(options.buttonClass).click(onButtonClick);                                                                                                 
                                                                                                           
    var originalBroswerWidth = document.body.clientWidth;                                                  
    window.onresize = function() {                                                                         
        if(options.resizeEvent) {                                                                          
            var newBroswerWidth = document.body.clientWidth;                                               
            var percent = newBroswerWidth / originalBroswerWidth;                                          
            container.width(container.width() * percent);                                                  
            scrollable.width(container.width() + left.width() + right.width());                            
            show();                                                                                        
        }                                                                                                  
        originalBroswerWidth = document.body.clientWidth;                                                  
    }                                                                                                      
                                                                                                          
    var scroll = false;                                                                                   
    move = function(distance) {                                                                           
        var newLeft = content.position.left + distance;                                                   
        var newRight = content.position.right + distance;                                                 
        if(distance > 0 && newLeft > container.position.left) {                                           
            distance = container.position.left - content.position.left;                                   
            scroll = false;                                                                               
        } else if(distance < 0 && newRight < container.position.right) {                                  
            distance = content.position.right - container.position.right;                                 
            scroll = false;                                                                               
        }                                                                                                 
        newLeft = content.position.left + distance;                                                       
        newRight = content.position.right + distance;                                                     
        scorll = beforeScroll ? beforeScroll(                                                             
                {left: content.position.left, right: content.position.right},                             
                {left: newLeft, right: newRight}) : scroll;                                               
        if(scroll) {                                                                                      
            content.css('left', newLeft + 'px');                                                          
            content.position.left += distance;                                                            
            content.position.right += distance;                                                           
            setTimeout('move(' + distance + ')', options.delay);                                          
        }                                                                                                 
    }                                                                                                     
    left.mouseover(function() {                                                                           
        //scroll = true;                                                                                    
        //move(options.speed);                                                                              
    });                                                                                                   
    right.mouseover(function() {                                                                          
        //scroll = true;                                                                                    
        //move(-options.speed);                                                                             
    });                                                                                                   
    left.mouseout(function() {                                                                            
        //scroll = false;                                                                                   
    });                                                                                                   
    right.mouseout(function() {                                                                           
        //scroll = false;                                                                                   
    });                                                                                                   
    left.mousedown(function() {                                                                           
        scroll = true;                                                                                    
        move(options.speedup);                                                                            
    });                                                                                                   
    right.mousedown(function() {                                                                          
        scroll = true;                                                                                    
        move(-options.speedup);                                                                           
    });                                                                                                   
    left.mouseup(function() {                                                                             
        scroll = false;                                                                                   
    });                                                                                                   
    right.mouseup(function() {                                                                            
        scroll = false;                                                                                   
    });                                                                                                   
}     