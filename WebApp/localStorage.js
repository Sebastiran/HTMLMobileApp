function submit() {
	//localStorage.removeItem("list");
	// Retrieve the entered form data
	var title = $('[name="title"]').val();
	var creator = $('[name="creator"]').val();
	var version = $('[name="version"]').val();
	var text = $('[name="text"]').val();

	var data = new Array();
	var list = getList();
	var img = getImages();
	// Push the new items into the existing list
	data.push({
		title : title,
		creator : creator,
		version : version,
		text : text,
		img : img
	});
	//Add the objects array to the list of pages
	list.push(data);
	saveList(list);

	localStorage.removeItem("images");
	// Reload the page to show the new objects
	window.location.reload();
}

function addImage() {
	// Retrieve the entered form data
	var image = $('[name="img"]').val();
	// Fetch the existing objects

	var images = getImages();
	// Push the new item into the existing list
	images.push({
		image : image
	});
	// Store the new list
	saveImages(images);
	// Reload the list
	postpage();
}

function getImages() {
	// See if objects is inside localStorage
	if (localStorage.getItem("images")) {
		// If yes, then load the objects
		images = JSON.parse(localStorage.getItem("images"));
	} else {
		// Make a new array of objects
		images = new Array();
	}
	return images;
}

function saveImages(images) {
	// Save the list into localStorage
	localStorage.setItem("images", JSON.stringify(images));
}

function getList() {
	// See if objects is inside localStorage
	if (localStorage.getItem("list")) {
		// If yes, then load the objects
		list = JSON.parse(localStorage.getItem("list"));
	} else {
		// Make a new array of objects
		list = new Array();
	}
	return list;
}

function saveList(list) {
	// Save the list into localStorage
	localStorage.setItem("list", JSON.stringify(list));
}

function postpage() {
	// Fetch the existing objects
	images = getImages();
	// Clear the list
	$('#images').find('li').remove();
	// Add every object to the objects list
	$.each(images, function(index, item) {
		var str = item.image;
		var split = str.split("\\");
		element = '<li>' + split[split.length - 1] + '</li>';
		$('#images').append(element);
	});

	$('#images').listview();
	$('#images').listview("refresh");
}


$(document).on('pagebeforeshow', '#post', function(event) {
	postpage();
});

function homepage() {
	// Fetch the existing objects
	list = getList();
	// Clear the list of items already displayed on the homepage
	$('#items').find('li').remove();
	// Show the title and the first images
	$.each(list, function(index, item) {
		element = '<li> <a href="page.html" rel="external" data-transition="slide" onclick="pagepage();"> <h1>' + item[0].title + '</h1> <img src=' + item[0].img.src + 'class="thumbnail" /> </a> </li>';
		$('#items').append(element);
	});

	$('#items').listview();
	$('#items').listview("refresh");
}


$(document).on('pagebeforeshow', '#home', function(event) {
	homepage();
});

function pagepage() {
	$("li").click(function() {
		var str = $(this).index();
	});
	
	list = getList();
	var element = list[0].title + '<br>' + 'Creator: ' + list[0].creator + '<br>' + 'Minecraftversion: ' + list[0].version;
	$('#info1').append(element);
	element = list[0].text;
	$('#info2').append(element);
}


$(document).on('pagebeforeshow', '#page', function(event) {
	pagepage();
});
