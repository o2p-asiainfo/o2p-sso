var Main = function() {
    var setModalSize = function() {
        $('.modal-body').css({
            'width': $(window).width() * 0.9 - 2,
            'height': $(window).height() - 120
        });
    }

    var handleTaskLists = function() {
        var url = $(this).attr("data-url");
        $(this).closest('.portlet').find('.portlet-body').html('<table class="table table-striped table-bordered table-hover" id="taskListsDataTable"> <thead> <tr> <th>First name2</th> <th>Last name</th> <th>Position</th> <th>Office</th> <th>Start date</th> <th>Salary</th> </tr> </thead> </table>');
        if (!jQuery().dataTable) {
            return;
        }
        $('#taskListsDataTable').dataTable({
            "processing": true,
            "serverSide": true,
            "lengthMenu": [5, 10, 15, "All"],
            "ajax": url,
        });
        jQuery('#taskListsDataTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
        jQuery('#taskListsDataTable_wrapper .dataTables_length select').addClass("form-control input-xsmall"); // modify table per page dropdown 

        //handle fullscreen view
        $('#cloneDataTable').bind('click', function() {
            $('#myModal .modal-body').html('<table class="table table-striped table-bordered table-hover" id="dataTableClone"> <thead> <tr> <th>First name</th> <th>Last name</th> <th>Position</th> <th>Office</th> <th>Start date</th> <th>Salary</th> </tr> </thead> </table>');
            setModalSize();
            if (!jQuery().dataTable) {
                return;
            }
            $('#dataTableClone').dataTable({
                "processing": true,
                "serverSide": true,
                "lengthMenu": [5, 10, 15, "All"],
                "ajax": url,
            });
            jQuery('#dataTableClone_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#dataTableClone_wrapper .dataTables_length select').addClass("form-control input-xsmall"); // modify table per page dropdown  

        });
    }

    var handleGaugeChart = function() {

        var el = jQuery(this).closest(".portlet").children(".portlet-body");
        var url = jQuery(this).attr("data-url");
        var target = jQuery(this).attr('data-target');
        App.blockUI({
            target: el,
            iconOnly: true
        });
        $.ajax({
            type: 'Get',
            cache: false,
            url: url,
            success: function(response, status, xhr) {
                App.unblockUI(el);
                var obj = eval('(' + response + ')');
                try {
                    if (obj.status == 0) {


                        require.config({
                            paths: {
                                'echarts': contextPath+'/resource/new/plugins/echarts/echarts',
                                'echarts/chart/gauge': contextPath+'/resource/new/plugins/echarts/echarts',
                            }
                        });

                        require(
                            [
                                'echarts',
                                'echarts/chart/gauge',
                            ],
                            function(ec) {

                                var chart = ec;
                                var myChart = ec.init(document.getElementById(target));

                                var labelBottom = {
                                    normal: {
                                        label: {
                                            show: false,
                                            position: 'bottom',
                                            textStyle: {
                                                baseline: 'bottom',
                                            }
                                        },
                                        labelLine: {
                                            show: false
                                        }
                                    }
                                };

                                var option = {
                                    tooltip: {
                                        formatter: "{a} <br/>{c} ms"
                                    },
                                    toolbox: {
                                        show: false,
                                    },
                                    series: [{
                                        name: '整体平均性',
                                        type: 'gauge',
                                        center: ['50%', '60%'], // 默认全局居中
                                        min: 0,
                                        max: 220,
                                        splitNumber: 11,
                                        axisLine: { // 坐标轴线
                                            lineStyle: { // 属性lineStyle控制线条样式
                                                width: 10
                                            }
                                        },
                                        axisTick: { // 坐标轴小标记
                                            length: 15, // 属性length控制线长
                                            lineStyle: { // 属性lineStyle控制线条样式
                                                color: 'auto'
                                            }
                                        },
                                        splitLine: { // 分隔线
                                            length: 20, // 属性length控制线长
                                            lineStyle: { // 属性lineStyle（详见lineStyle）控制线条样式
                                                color: 'auto'
                                            }
                                        },
                                        title: {
                                            textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                                fontWeight: 'bolder',
                                                fontSize: 20,
                                                fontStyle: 'italic'
                                            },
                                            offsetCenter: [0, '-125%'], // x, y，单位px
                                        },
                                        detail: {
                                            textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                                fontWeight: 'bolder'
                                            },
                                            formatter: '{value}s',
                                        },
                                        data: [{
                                            value: 40,
                                            name: '整体平均性能'
                                        }]
                                    }, {
                                        name: '服务提供方平均性能',
                                        type: 'gauge',
                                        center: ['15%', '65%'], // 默认全局居中
                                        radius: '50%',
                                        min: 0,
                                        max: 100,
                                        endAngle: 45,
                                        splitNumber: 10,
                                        axisLine: { // 坐标轴线
                                            lineStyle: { // 属性lineStyle控制线条样式
                                                width: 8
                                            }
                                        },
                                        axisTick: { // 坐标轴小标记
                                            length: 12, // 属性length控制线长
                                            lineStyle: { // 属性lineStyle控制线条样式
                                                color: 'auto'
                                            }
                                        },
                                        splitLine: { // 分隔线
                                            length: 20, // 属性length控制线长
                                            lineStyle: { // 属性lineStyle（详见lineStyle）控制线条样式
                                                color: 'auto'
                                            }
                                        },
                                        pointer: {
                                            width: 5
                                        },
                                        title: {
                                            offsetCenter: [0, '-135%'], // x, y，单位px
                                        },
                                        detail: {
                                            textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                                fontWeight: 'bolder'
                                            },
                                            formatter: '{value}ms',
                                        },
                                        axisLabel: {
                                            formatter: function(v) {
                                                switch (v + '') {
                                                    case '0':
                                                        return '0';
                                                    case '50':
                                                        return '50';
                                                    case '100':
                                                        return '100';
                                                }
                                            }
                                        },
                                        data: [{
                                            value: 88,
                                            name: '服务提供方平均性能'
                                        }]
                                    }, {
                                        name: 'O2P平均性能',
                                        type: 'gauge',
                                        center: ['85%', '65%'], // 默认全局居中
                                        radius: '50%',
                                        min: 0,
                                        max: 100,
                                        startAngle: 135,
                                        endAngle: -45,
                                        splitNumber: 10,
                                        axisLine: { // 坐标轴线
                                            lineStyle: { // 属性lineStyle控制线条样式
                                                color: [
                                                    [0.2, '#228b22'],
                                                    [0.8, '#48b'],
                                                    [1, '#ff4500']
                                                ],
                                                width: 8
                                            }
                                        },
                                        axisTick: { // 坐标轴小标记
                                            splitNumber: 3,
                                            length: 12, // 属性length控制线长
                                            lineStyle: { // 属性lineStyle控制线条样式
                                                color: 'auto'
                                            }
                                        },
                                        axisLabel: {
                                            formatter: function(v) {
                                                switch (v + '') {
                                                    case '0':
                                                        return '0';
                                                    case '50':
                                                        return '50';
                                                    case '100':
                                                        return '100';
                                                }
                                            }
                                        },
                                        splitLine: { // 分隔线
                                            length: 20, // 属性length控制线长
                                            lineStyle: { // 属性lineStyle（详见lineStyle）控制线条样式
                                                color: 'auto'
                                            }
                                        },
                                        pointer: {
                                            width: 5
                                        },
                                        title: {
                                            offsetCenter: [0, '-135%'], // x, y，单位px
                                        },
                                        detail: {
                                            textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                                fontWeight: 'bolder'
                                            },
                                            formatter: '{value}ms',
                                        },
                                        data: [{
                                            value: 56,
                                            name: 'O2P平均性能'
                                        }]
                                    }]
                                };
                                myChart.clear();   
                                myChart.setOption(option);
                                $('#' + target).closest('.portlet').find('.portlet-title .tools .s-fullscreen').bind('click', function() {
                                    $('#myModalLabel').html(obj.data.name);
                                    var fullChart = chart.init(document.getElementById('fullChart'));
                                    setModalSize();
                                    fullChart.clear();
                                    fullChart.setOption(option, true);
                                })
                                $('body').bind('click', function() {
                                    setTimeout(function() {
                                        myChart.resize && myChart.resize();
                                    }, 1)
                                })
                            }
                        );

                    } else {
                        el.html(obj.msg);
                    }
                } catch (err) {

                }
            },
            error: function(xhr, ajaxOptions, thrownError) {
                App.showAjaxErrorMask();
            }
        });
    }

    var handleLineChart = function() {
        var el = jQuery(this).closest(".portlet").children(".portlet-body");
        var url = jQuery(this).attr("data-url");
        var target = jQuery(this).attr('data-target');
        App.blockUI({
            target: el,
            iconOnly: true
        });
        $.ajax({
            type: 'Get',
            cache: false,
            url: url,
            success: function(response, status, xhr) {
                App.unblockUI(el);
                var obj = eval('(' + response + ')');
                try {
                    if (obj.status == 0) {

                        require.config({
                            paths: {
                                'echarts': contextPath+'/resource/new/plugins/echarts/echarts',
                                'echarts/chart/line': contextPath+'/resource/new/plugins/echarts/echarts',
                                'echarts/chart/bar': contextPath+'/resource/new/plugins/echarts/echarts',
                            }
                        });

                        require(
                            [
                                'echarts',
                                'echarts/chart/line',
                                'echarts/chart/bar',
                            ],
                            function(ec) {
                                var chart = ec;
                                var myChart = ec.init(document.getElementById(target));

                                var option = {
                                    tooltip: {
                                        trigger: 'axis',
                                        formatter: '{a}:{b}'
                                    },
                                    legend: {
                                        data: [obj.data.yAxisNameA, obj.data.yAxisNameB, obj.data.yAxisNameC],
                                    },
                                    color: ['#ff7f50', '#87cefa', '#da70d6'],
                                    dataZoom: {
                                        show: true,
                                        realtime: true,
                                        start: 50,
                                        end: 100
                                    },
                                    grid: {
                                        x: 30,
                                        y: 50,
                                        x2: 50,
                                        y2: 60,
                                    },
                                    xAxis: [{
                                        type: 'category',
                                        splitLine: {
                                            show: false
                                        },
                                        data: obj.data.yAxisA
                                    }],
                                    yAxis: [{
                                        type: 'value',
                                    }, {
                                        type: 'value',
                                        name: obj.data.yAxisNameA,
                                        axisLabel: {
                                            formatter: '{value} s'
                                        }
                                    }],
                                    series: [{
                                        name: obj.data.yAxisNameC,
                                        type: 'bar',
                                        tooltip: {
                                            trigger: 'item',
                                            formatter: '{a}<br>[{b}] {c}'
                                        },
                                        stack: 'Service',
                                        data: obj.data.yAxisC
                                    }, {
                                        name: obj.data.yAxisNameB,
                                        type: 'bar',
                                        tooltip: {
                                            trigger: 'item',
                                            formatter: '{a}<br>[{b}] {c}'
                                        },
                                        stack: 'Service',
                                        data: obj.data.yAxisB
                                    }, {
                                        name: obj.data.yAxisNameA,
                                        type: 'line',
                                        yAxisIndex: 1,
                                        data: obj.data.yAxisA
                                    }]
                                };
                                myChart.clear();                                
                                myChart.setOption(option);
                                $('#' + target).closest('.portlet').find('.portlet-title .tools .s-fullscreen').bind('click', function() {
                                    $('#myModalLabel').html(obj.data.chartName);
                                    var fullChart = chart.init(document.getElementById('fullChart'));
                                    setModalSize();
                                    fullChart.clear();
                                    fullChart.setOption(option, true);
                                })
                                $('body').bind('click', function() {
                                    setTimeout(function() {
                                        myChart.resize && myChart.resize();
                                    }, 1)
                                })
                            }
                        );

                    } else {
                        el.html(obj.msg);
                    }
                } catch (err) {

                }
            },
            error: function(xhr, ajaxOptions, thrownError) {
                App.showAjaxErrorMask();
            }
        });
    }


    var handleDropMenu = function() {
        $('body').on('click', '.portlet-title .dropdown-menu > li > a', function() {
            var txt = $(this).text();
            var url = $(this).attr('data-url');
            $(this).closest('.portlet-title').find('a.reload').attr('data-url', url)
            $(this).closest('.dropdown-menu').prev('a').html(txt + '<span class="caret"></span>');
            $(this).closest('.portlet-title').find('.tools a.reload').trigger('click');
        })
    }

    return {

        //main function to initiate the page 
        init: function() {
            $('#reloadDataTable').bind('click', handleTaskLists).click();
            $('#three').closest('.portlet').find('.portlet-title > .tools > a.reload').bind('click', handleGaugeChart).click();
            $('#businessAnalysisEchart').closest('.portlet').find('.portlet-title > .tools > a.reload').bind('click', handleLineChart).click();;
            $('#systemPerformanceEchart').closest('.portlet').find('.portlet-title > .tools > a.reload').bind('click', handleLineChart).click();
            $('#operatorInformationPortEchart').closest('.portlet').find('.portlet-title > .tools > a.reload').bind('click', handleLineChart).click();

            handleDropMenu();
            setModalSize();
        
        },
        fnBody : function(){
            $('body').trigger('click');            
        }
    }

}();
