<!-- iframe 跨域 -->
    <iframe id="iframecommon"  style="display:none" ></iframe>
    <script type="text/javascript">
       var vCustomHeight;
       function changeTopScrollHeight(vHeight){
       	     var ssoHeight;       	     
       		 if(vHeight != undefined){
       		 	 vCustomHeight = vHeight;
       		 }        		 
       		 if (vCustomHeight!= undefined) {
       		 	ssoHeight = vCustomHeight;
       		 }else {
	       		ssoHeight = document.body.scrollHeight;
             }
             //alert("ssoHeight:"+ssoHeight+"vCustomHeight:"+vCustomHeight+"vHeight:"+vHeight);
             var ssoIframecommon = document.getElementById('iframecommon');	   
             //ssoHeight =ssoHeight+200;
             //<!-- 自适应高度  IP+port和UAP　main.jsp相同即可（或域名和port）；可以不是同一应用  -->
             //http://sso.eaap.asiainfo-linkage.com:9083/EAAP_SSO/main/proxy.html?random=      
	         //ssoIframecommon.src= "../main/proxy.html?random="+(Math.random()*100000000)+"#" + ssoHeight;
	         ssoIframecommon.src= "../main/proxy.html#" + ssoHeight;
       } 
       
       $(function() {
       	 changeTopScrollHeight();
	   });
	   
	   $(window).resize(function () {
            changeTopScrollHeight();
       });
       //document.body.onresize = function(){alert('hehe');changeTopScrollHeight();};
    </script>
  