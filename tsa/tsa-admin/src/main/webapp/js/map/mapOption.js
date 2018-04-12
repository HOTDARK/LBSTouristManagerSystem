$(function () {
    var map = new BMap.Map("container"); //在container容器中创建一个地图,参数container为div的id属性;
    var point = new BMap.Point(117.946814, 40.990161);
    map.centerAndZoom(point, 16);
    map.enableScrollWheelZoom(); //激活滚轮调整大小功能
    map.addControl(new BMap.NavigationControl()); //添加控件：缩放地图的控件，默认在左上角；
    map.addControl(new BMap.MapTypeControl()); //添加控件：地图类型控件，默认在右上方；
    map.addControl(new BMap.ScaleControl()); //添加控件：地图显示比例的控件，默认在左下方；
    map.addControl(new BMap.OverviewMapControl()); //添加控件：地图的缩略图的控件，默认在右下方； TrafficControl
    getPlace();

    /*    var geolocation = new BMap.Geolocation();
        geolocation.getCurrentPosition(function(r){
            if(this.getStatus() == BMAP_STATUS_SUCCESS){
                var mk = new BMap.Marker(r.point);
                map.addOverlay(mk);
                getPlace();
                map.panTo(r.point);
            }
            else {
                alert('failed'+this.getStatus());
            }
        },{enableHighAccuracy: true});*/
    if (map != null) {
        map.addEventListener("click", function (e) {
            var x = e.point.lng + "";
            var y = e.point.lat + "";
            document.getElementById("lonlat").value = x;
            document.getElementById("lonlat2").value = y;
        });
    }

    var search = new BMap.LocalSearch("中国", {
        onSearchComplete: function (result) {
            if (search.getStatus() == BMAP_STATUS_SUCCESS) {
                var res = result.getPoi(0);
                var point = res.point;
                map.centerAndZoom(point, 11);
            }

        },
        renderOptions: { //结果呈现设置，
            map: map,
            autoViewport: true,
            selectFirstResult: true
        },
        onInfoHtmlSet: function (poi, html) {
            // alert(html.innerHTML)
        }

    });

//设置城市的函数
    function setCity() {
        search.search(document.getElementById("cityName").value);
    }
    function setInfo(obj){
        document.getElementById("editId").value = obj.placeId;
        $("#remark").html(obj.remark);
    }

    function getPlace() {
        data = {};
        url = "/tsa/location/getPlace.action";
        $.ajaxSetup({
            async: false
        });
        $.post(url, data, function (data) {
            if ("success" == data.message) {
                $.each(data.data, function (i, item) {
                    //alert(item.remark);
                    var myicon = new BMap.Icon(
                        '/tsa/images/map/' + item.type + '.png', // 百度图片place1.png
                        new BMap.Size(10, 22), // 视窗大小
                        {
                            imageSize: new BMap.Size(31, 21), // 引用图片实际大小
                            imageOffset: new BMap.Size(-10, 0)  // 图片相对视窗的偏移
                        }
                    );
                    position = item.location.split(",");
                    var mkr = new BMap.Marker(new BMap.Point(position[0], position[1]), {icon: myicon});

                    var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + item.remark + "</p>");
                    mkr.addEventListener("click", function () {
                        setInfo(item);
                        this.openInfoWindow(infoWindow);
                    });
                    map.addOverlay(mkr);
                });
            }
        }, "json");

    }


});


var type = null;

function addPlace(type) {
    this.type = type;
    var x = $("#lonlat").val();
    var y = $("#lonlat2").val();
    var userID = $("#currentUserID").val();
    var remark = $("#remark").val();

    if(remark == undefined ){
        alert("描述不能为空");
    }
    alert(remark);
    var posi = x + "," + y;
    if (type != null) {
        data = {"type": type, "location": posi, "createdBy": userID,"remark":remark};
        url = "/tsa/location/addPlace.action";
        $.post(url, data, function (data) {
            alert(data.message);
            document.getElementById("remark").innerHTML = "";
        }, "json");
        id = null;
    }
}
function clickEdit(type) {
    id = document.getElementById("editId").value;
    remark = $("#remark").val();
    if(type == 1){
        data = {"placeId": id, "remark": remark};
        url = "/tsa/location/updatePlace.action";
        $.post(url, data, function (data) {
            alert(data.message);
        }, "json");
    }else if(type == 2){
        data = {"placeId": id};
        url = "/tsa/location/deletePlace.action";
        $.post(url, data, function (data) {
            alert(data.message);
        }, "json");
    }
    //alert(remark);
}