$(function () {
    var map = new BMap.Map("containerShowMap"); //在container容器中创建一个地图,参数container为div的id属性;
    var point = new BMap.Point(116.331398,39.897445);
    map.centerAndZoom(point,12);
    map.enableScrollWheelZoom(); //激活滚轮调整大小功能
    map.addControl(new BMap.NavigationControl()); //添加控件：缩放地图的控件，默认在左上角；
    map.addControl(new BMap.MapTypeControl()); //添加控件：地图类型控件，默认在右上方；
    map.addControl(new BMap.ScaleControl()); //添加控件：地图显示比例的控件，默认在左下方；
    map.addControl(new BMap.OverviewMapControl()); //添加控件：地图的缩略图的控件，默认在右下方； TrafficControl


    var geolocation = new BMap.Geolocation();
    geolocation.getCurrentPosition(function(r){
        if(this.getStatus() == BMAP_STATUS_SUCCESS){
            var mk = new BMap.Marker(r.point);
            map.addOverlay(mk);
            map.panTo(r.point);
        }
        else {
            alert('failed'+this.getStatus());
        }
    },{enableHighAccuracy: true});
    
});