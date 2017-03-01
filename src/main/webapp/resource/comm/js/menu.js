
	var outerLayout;

	/*
	*#######################
	*     ON PAGE LOAD
	*#######################
	*/
	$(document).ready( function() {
		// create the OUTER LAYOUT
		outerLayout = $("body").layout( layoutSettings_Outer );

		/*******************************
		 ***  CUSTOM LAYOUT BUTTONS  ***
		 *******************************
		 *
		 * Add SPANs to the east/west panes for customer "close" and "pin" buttons
		 *
		 * COULD have hard-coded span, div, button, image, or any element to use as a 'button'...
		 * ... but instead am adding SPANs via script - THEN attaching the layout-events to them
		 *
		 * CSS will size and position the spans, as well as set the background-images
		 */

		// BIND events to hard-coded buttons in the NORTH toolbar
		/*outerLayout.addToggleBtn( "#tbarToggleNorth", "north" );
		outerLayout.addOpenBtn( "#tbarOpenSouth", "south" );
		outerLayout.addCloseBtn( "#tbarCloseSouth", "south" );
		outerLayout.addPinBtn( "#tbarPinWest", "west" );
		outerLayout.addPinBtn( "#tbarPinEast", "east" );*/

		// save selector strings to vars so we don't have to repeat it
		// must prefix paneClass with "body > " to target ONLY the outerLayout panes
		var westSelector = "body > .ui-layout-west"; // outer-west pane
		//var eastSelector = "body > .ui-layout-east"; // outer-east pane

		 // CREATE SPANs for pin-buttons - using a generic class as identifiers
		/*$("<span></span>").addClass("pin-button").prependTo( westSelector );
		$("<span></span>").addClass("pin-button").prependTo( eastSelector );
		// BIND events to pin-buttons to make them functional
		outerLayout.addPinBtn( westSelector +" .pin-button", "west");
		outerLayout.addPinBtn( eastSelector +" .pin-button", "east" );*/

		 // CREATE SPANs for close-buttons - using unique IDs as identifiers
		$("<span></span>").attr("id", "west-closer" ).prependTo( westSelector );
		//$("<span></span>").attr("id", "east-closer").prependTo( eastSelector );
		// BIND layout events to close-buttons to make them functional
		outerLayout.addCloseBtn("#west-closer", "west");
		//outerLayout.addCloseBtn("#east-closer", "east");


		/* Create the INNER LAYOUT - nested inside the 'center pane' of the outer layout
		 * Inner Layout is create by createInnerLayout() function - on demand
		 *
			innerLayout = $("div.pane-center").layout( layoutSettings_Inner );
		 *
		 */


		// DEMO HELPER: prevent hyperlinks from reloading page when a 'base.href' is set
		$("a").each(function () {
			var path = document.location.href;
			if (path.substr(path.length-1)=="#") path = path.substr(0,path.length-1);
			if (this.href.substr(this.href.length-1) == "#") this.href = path +"#";
		});

	});


	/*
	*#######################
	* OUTER LAYOUT SETTINGS
	*#######################
	*
	* This configuration illustrates how extensively the layout can be customized
	* ALL SETTINGS ARE OPTIONAL - and there are more available than shown below
	*
	* These settings are set in 'sub-key format' - ALL data must be in a nested data-structures
	* All default settings (applied to all panes) go inside the defaults:{} key
	* Pane-specific settings go inside their keys: north:{}, south:{}, center:{}, etc
	*/
	var layoutSettings_Outer = {
		name: "outerLayout" // NO FUNCTIONAL USE, but could be used by custom code to 'identify' a layout
		// options.defaults apply to ALL PANES - but overridden by pane-specific settings
	,	defaults: {
			size:					"auto"
		,	minSize:				50
		,	paneClass:				"pane" 		// default = 'ui-layout-pane'
		,	resizerClass:			"resizer"	// default = 'ui-layout-resizer'
		,	togglerClass:			"toggler"	// default = 'ui-layout-toggler'
		,	buttonClass:			"button"	// default = 'ui-layout-button'
		,	contentSelector:		".content"	// inner div to auto-size so only it scrolls, not the entire pane!
		,	contentIgnoreSelector:	"span"		// 'paneSelector' for content to 'ignore' when measuring room for content
		,	togglerLength_open:		35			// WIDTH of toggler on north/south edges - HEIGHT on east/west edges
		,	togglerLength_closed:	35			// "100%" OR -1 = full height
		,	hideTogglerOnSlide:		true		// hide the toggler when pane is 'slid open'
		,	togglerTip_open:		"Close This Pane"
		,	togglerTip_closed:		"Open This Pane"
		,	resizerTip:				"Resize This Pane"
		//	effect defaults - overridden on some panes
		,	fxName:					"slide"		// none, slide, drop, scale
		,	fxSpeed_open:			750
		,	fxSpeed_close:			1500
		,	fxSettings_open:		{ easing: "easeInQuint" }
		,	fxSettings_close:		{ easing: "easeOutQuint" }
	}
	,	north: {
		maxSize:				80
		,	spacing_open:			10			// cosmetic spacing
		,	togglerLength_open:		0			// HIDE the toggler button
		,	togglerLength_closed:	-1			// "100%" OR -1 = full width of pane
		,	resizable: 				false
		,	slidable:				false
		//	override default effect
		,	fxName:					"none"
		,closable:false
		}
	,	west: {
			spacing_closed:			20			// wider space when closed
		,	resizable: 				true
		,	slidable:				true
		,	spacing_open:			10			// cosmetic spacing
		,	togglerLength_closed:	20			// make toggler 'square' - 21x21
		,	togglerAlign_closed:	"top"		// align to top of resizer
		,	togglerLength_open:		0			// NONE - using custom togglers INSIDE west-pane
		,	togglerTip_open:		"Close West Pane"
		,	togglerTip_closed:		"Open West Pane"
		,	resizerTip_open:		"Resize West Pane"
		,	slideTrigger_open:		"click" 	// default
		,	initClosed:				false
		//	add 'bounce' option to default 'slide' effect
		,	fxSettings_open:		{ easing: "easeOutBounce" }
		}
	,	center: {
			paneSelector:			"#mainContent" 			// sample: use an ID to select pane instead of a class
		,	minWidth:				200
		,	minHeight:				200
		, size:'100%'
		}
	};

	$(function() {  
	    scrollable('#scrollable_content', '#scrollable_render', {  
	          
	    }, function(originalPosition, newPosition) {  
	        return true;  
	    });  
	}); 
	
	var icons = {
				header: "ui-icon-circle-shrink",
				headerSelected: "ui-icon-circle-outspread"
			};
	


		function editStaffInfo(url){
		  MM_openBrWindow(url,'��Ա��Ϣ�༭');
		}

			function fun_hiddenTopCote(objImg){
				if($("#main-top").hasClass('main-top-mini')){
					fun_topCote('normal');
				} else {
					fun_topCote('mini');
				}
				outerLayout.resizeAll();
			}
			function fun_topCote(topClass){
				var objImg = document.getElementById("logoImgBtn");
				if(topClass=='mini'){
					$("#main-top").addClass("main-top-mini");
					$("#main-top-content").addClass("main-top-content-mini");
					SetCookie("op2-main-top-class","mini");
					objImg.src="../resource/blue/images/stick.png"
				}else{
					$("#main-top").removeClass("main-top-mini");
					$("#main-top-content").removeClass("main-top-content-mini");
					SetCookie("op2-main-top-class","normal");
					objImg.src="../resource/blue/images/stuck.png";
				}
			}
			
			$(function() {
				var mainTopClass = getCookie("op2-main-top-class");
				fun_topCote(mainTopClass);
			});
	var $tabs
	$(function() {
		var tab_counter = 3;
		// tabs init with a custom tab template and an "add" callback filling in the content
		$tabs = $( "#tabs").tabs({
			tabTemplate: "<li><a href='#{href}'>#{label}</a> <span class='ui-icon ui-icon-close'>Remove Tab</span></li>"
			,add: function( event, ui ) {
				var tab_content =  "Tab " + tab_counter + " content.";
				$( ui.panel ).append( "<p>" + tab_content + "</p>" );
			}
			,select:function( event, ui ) {}
			,show:function(obj,tab){   
				$(tab.panel).height($("#mainContent").height()-30);
				//$(tab.panel).find('IFRAME').css("height",tab.panel.scrollHeight);
			}   

		});
		$tabs.find( ".ui-tabs-nav" ).sortable({ axis: "x" });

		// close icon: removing the tab on click
		// note: closable tabs gonna be an option in the future - see http://dev.jqueryui.com/ticket/3924
		$( "#tabs span.ui-icon-close" ).live( "click", function() {
			var index = $( "li", $tabs ).index( $( this ).parent() );
			$tabs.tabs( "remove", index );
		});


	});
			// actual addTab function: adds new tab using the title input from the form above
		function addTab(id,title,url) {
			var pageId="Page"+id;
			$.ajax({
			   		type: "post",
			   		url: "index!redirectSendTicketToLocalSystem.shtml",
			   		data: {oldUrl:url,localSystemId:id},
			       	dataType: "text",
			       	success: function (data) {
			   			url = data
						$tabs.tabs( "add", url, title ,pageId);
			   			
			   		}
			   		});
			
			//$tabs.tabs( "select", pageId);
			//$tabs.tabs( "enable", id);
		}
	
	function getAccordionJson(parentId) {
  		var div = document.getElementById("accordion"); 
  	    while(div.hasChildNodes()) 
  	    {
  	        div.removeChild(div.firstChild);
  	    }
  	 /*  var parentId = document.getElementById("showMain").children().attr("id");
  	  alert(parentId); */
  	  $.ajax({
	   		type: "post",
	   		url: "index!showLeftMain.shtml",
	   		data: {parentMenuId:parentId},
	       	dataType: "json",
	       	success: function (data) {
		        	$.each(data, function(i, item) {
		        		var str="";
		        		str="<h3><a href=\"#\">"+item.name+"</a></h3>"+
		        		"<div id=\"zTree\" style=\"text-align:left;padding:0px 0px 0px 15px; margin:0;\"><P><ul id=\"treeDemo"+item.id+"\" class=\"ztree\" ></ul></div>"; 
		        		$( "#accordion" ).append(str);
		        		$( "#accordion" ).accordion("destroy");
		        		$( "#accordion" ).accordion({
		        			active:true,
		    				collapsible: true, 
		    				fillSpace: true,
		    				icons: icons
		    			});
		        		var zTreeId = item.id;
		        		var setting = {
		        				view: {
		        					showIcon: showIconForTree
		        					,showLine: false
		        				},
		        				data: {
		        					simpleData: {
		        						enable: true
		        					}
		        				},
		        				async: {
		        		                enable: true,
		        		               //contentType: "application/json",
		        		                url: "index!showMenuAndChildrenMenus.shtml?childId="+zTreeId,
		        		                         
		        		        },
		        				folder: {
		        					simpleData: {
		        						enable: true
		        					}
		        				}
		        			}; 
		        			
		        			function showIconForTree(treeId, treeNode) {
		        				return !treeNode.isParent;
		        			};
		        			var zNodes =[];
		        			$.fn.zTree.init($("#treeDemo"+item.id), setting, zNodes);
		        	});
	       	},
	       	error: function (XMLHttpRequest, textStatus, errorThrown) {
	              alert(errorThrown);
	       	}
		});
  		
  	}
	  	$.ajax({
	   		type: "post",
	   		url: "index!showMain.shtml",
	       	dataType: "json",
	       	success: function (data) {
		        	$.each(data, function(i, item) {
		        		var str="";
		        		str="<div id=\"showMain\"class=\"button\">"+
		        				"<div class=\"button_left\"></div>"+
		        					"<div class=\"button_center\">"+
		        						"<span id="+item.id+">"+ item.name +"</span></div>"+  
		        					"<div class=\"button_right\"></div>"+
		        			"</div>";
		        		$("#scrollable_content").append(str);
		        		if(i==0){
		        			$("#headerId").html(item.name);
		        			getAccordionJson(item.id);
		        		}
		        	});
		        		
	       	},
	       	error: function (XMLHttpRequest, textStatus, errorThrown) {
	              alert(errorThrown);
	       	}
	       	
		});
	  	$("#showMain").live("click", function(){
	  		var parentId = $(this).find("span").attr("id");
	  		var value = $(this).find("span").html();
	  		$("#headerId").html(value);
	  		getAccordionJson(parentId);
		});
	  	
	  	  	
