function timeSelectGenerate(yearId, monthId, dayId, yearBegin, yearEnd){
    var year = $('#' + yearId);
    var month = $('#' + monthId);
    var day = $('#' + dayId);
    var y, m, d=0;
    var yearHtml = "<option value='0'></option>";
    var monthHtml = "<option value='0'></option>";
    var dayHtml = "<option value='0'></option>";
    for(y = yearBegin; y < yearEnd; y++){
        yearHtml = yearHtml + "<option value='"+y+"'>"+y+"</option>";
    }
    year.html(yearHtml);//生成年份下拉菜单

    for(m = 1; m <= 12; m++){
        monthHtml = monthHtml + "<option value='"+m+"'>"+m+"</option>";
    }
    month.html(monthHtml);//生成月份下拉菜单

    for(d = 1; d <= 31; d++){
        dayHtml = dayHtml + "<option value='" + d + "'>" + d + "</option>";
    }
    day.html(dayHtml);
    /*
     * 处理每个月有多少天---联动
     */
    month.change(function(){
        var total;
        var currentYear = year.val();
        var currentMonth = month.val();
        switch(currentMonth){
            case "1" :
            case "3" :
            case "5" :
            case "7" :
            case "8" :
            case "10" :
            case "12" :
                total = 31;
                break;
            case "4" :
            case "6" :
            case "9" :
            case "11":
                total = 30;
                break;
            case "2" :
                if((currentYear%4 == 0 && currentYear%100 != 0) || currentYear%400 == 0){
                    total = 29;
                }else{
                    total = 28;
                }
                break;
            default:
                break;
        }
        dayHtml = "<option value='0'></option>";
        for(d = 1;d <= total;d++){
            dayHtml = dayHtml + "<option value='"+d+"'>"+d+"</option>";
        }
        day.html(dayHtml);//生成日期下拉菜单
    })
}