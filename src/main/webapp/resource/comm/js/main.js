
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
			$tabs.tabs( "add", url, title ,pageId);
			//$tabs.tabs( "select", pageId);
			//$tabs.tabs( "enable", id);
		}
