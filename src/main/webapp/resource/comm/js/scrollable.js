scrollable = function(content, render, options, beforeScroll) {                                            
    /*                                                                                                     
     * @author: selfimpr                                                                                   
     * @blog: http://blog.csdn.net/lgg201                                                                  
     * @e-mail: lgg860911@yahoo.com.cn                                                                     
     *                                                                                                     
     * ע��:                                                                                               
     *      1. content�����Լ�ָ�����. ������е�Ԫ��ʹ�ÿ�Ԫ��, ��ʹ��float: left���󸡶�.               
     *      2. ʹ��ʱ, �����Զ�����ʽ, ���ڱ���ˮƽǷ��, ������������ͨ�õĶ���, �Ǻ�.                     
     *                                                                                                     
     * ��������                                                                                            
     * content: ����Ԫ��, ������ѡ������JQUERY��װ��DOMԪ��                                                
     * render: ��Ⱦ����Ŀ������, ������ѡ������JQUERY��װ��DOMԪ��                                         
     * options: ѡ��                                                                                       
     *      scrollable_class: ����scrollable��������ʽ , Ĭ��: ui-scrollable                             
     *      scrollable_left_class: ��ť����ʽ, Ĭ��: ui-scrollable-left                                  
     *      scrollable_container_class: ������������ʽ, Ĭ��: ui-scrollable-container                      
     *      scrollable_right_class: �Ұ�ť����ʽ, Ĭ��: ui-scrollable-right                                
     *      delay: �����ϻ�����ťʱ�����ƶ�֮���ʱ����, ����                                        
     *      speed: �����ϰ�ťʱ, һ���ƶ��ľ���, ����                                                    
     *      speedup: �����°�ťʱ, һ���ƶ��ľ���, ����                                                  
     *      resizeEvent: �Ƿ�������ڸı��С���¼�, ����ֵ,                                               
     *          �������ڸı��Сʱ, ��ˢ��ҳ���, �о���ʾ�е��Ť, ����Ĭ����false                        
     * beforeScroll: ���ݹ���ʱ����¼��ص�����.                                                           
     *      ���ܲ���(��������): ��һ���ǹ���ǰ��������λ��, �ڶ����ǹ�������������λ��.                    
     *      ע��: ���¼�����ʹ���ݲ��ܱ߽����ƵĹ���.                                                      
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