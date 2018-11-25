var endpoint = "./api/";
/*
 * 旅行記リストを作成する
 * */
var createTravelogueList = function(travelogue) {
	//var photo = travelogue.photo;
	//var photo = getPhoto(travelogue.photos[0]);
	//var photo = getPhoto(1);
	//console.log("photo:" + photo);
	console.log("travelogueID:" + travelogue.id);
	var photo = getPhoto(travelogue.photos[0]);
	$("#travelogue-lists").append(
			'<a href="card.html?' + travelogue.id + '" style="color: gray; text-decoration:none">'
			+ '<div class="card"><div class="row"><div class="col d-flex">'
			+ '<div class="col-md-6">'
			+ '<img id="traveloguePhoto" class="img-keep-ratio my-4" height="250" src="' + photo + '">'
			+ '</div>'
			+ '<div class="card-body col-md-6">'
			+ '<small class="text-muted">' + travelogue.date + '</small>'
			+ '<h5 class="card-title">' + travelogue.title.substr(0,10) + '</h5>'
			+ '<p class="float-right" style="font-size: 0.6rem">by ' + travelogue.author + '</p>'
			+ '</div></div></div></div></a>'
			);
	//getPhoto(travelogue.photos[0]);
}
var createTravelogueLists = function() {
	console.log("createLists");
	$.ajax({
		type: 'GET',
		url: endpoint + 'travelogues',
		contentType: 'image/json',
		success: function(json) {
			console.log(json.travelogues.length);
			for(var i=0; i < json.travelogues.length; i++){
				console.log(json.travelogues[i]);
				createTravelogueList(json.travelogues[i]);
			}
		}
	});
}

/*
 * 旅行記の写真を取得する
 * */
var getPhoto = function(photos) {
	return 'data:image/jpeg;base64,' + photos.rawImage;
	/*$.ajax({
		type: 'GET',
		url: endpoint + 'photos/' + photoID + '/raw',
		contentType: 'image/jpeg',
		success: function(json){
			$('#traveloguePhoto' + photoID).attr("src",'data:image/jpeg;base64,' + json.rawImage);
		}
	});*/
}

var travelogues = [{
	  "id": null,
	  "title":"イリヤかわいいあいうえおかきくけこ",
	  "date": "2018-09-18",
	  "author": "itimon",
	  "photos": [1],
	  "photo": "http://tn.smilevideo.jp/smile?i=24770287.L",
	  "description": "あいうえおかきくけこさしすせそ"
	},{
	  "id": null,
	  "title":"イリヤかわいい2",
	  "date": "2018-09-18",
	  "author": "itimon",
	  "photos": [2],
	  "photo": "http://tn.smilevideo.jp/smile?i=24770287.L",
	  "description": "あいうえおかきくけこさしすせそ"
	}];

var changeFooterURL = function(){
	if(window.sessionStorage.getItem(['access_token']) != null){
		return "post.html";
	}else{
		return "login.html";
	}
}

$(document).ready(function(){
	console.log("ready");
	createTravelogueLists();
});
