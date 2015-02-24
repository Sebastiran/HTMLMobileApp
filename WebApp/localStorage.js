function submit() {
	//Clear the list of objects
	localStorage.removeItem("objects");
	// Retrieve the entered form data
	var title = $('[name="title"]').val();
	var creator = $('[name="creator"]').val();
	var version = $('[name="version"]').val();
	var text = $('[name="text"]').val();
	// Fetch the existing objects
	var objects = getObjects();
	// Push the new item into the existing list
	objects.push(title, creator, version, text);
	// Clear the images list for the next use
	//localStorage.removeItem("images");
	// Store the new list
	saveObjects(objects);
	// Reload the page to show the new objects
	window.location.reload();
}

function getObjects() {
	// See if objects is inside localStorage
	if (localStorage.getItem("objects")) {
		// If yes, then load the objects
		objects = JSON.parse(localStorage.getItem("objects"));
	} else {
		// Make a new array of objects
		objects = new Array();
	}
	return objects;
}

function saveObjects(objects) {
	// Save the list into localStorage
	localStorage.setItem("objects", JSON.stringify(objects));
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

function postpage() {
	// Fetch the existing objects
	images = getImages();
	// Clear the list
	$('#images').find('li').remove();
	// Add every object to the objects list
	$.each(images, function(index, item) {
		var str = item.image;
		var split =	str.split("\\");
		element = '<li>' + split[split.length-1] + '</li>';
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
	objects = getObjects();
	images = getImages();
	// Clear the list
	//$('#items').find('li').remove();
	// Show the title and the first images
	element = '<li>' + '<h1>' + objects[0] + '</h1>' + '<img src=' + images[0] + 'class="thumbnail" />' + '</li>';
	$('#items').append(element);

	$('#items').listview();
	$('#items').listview("refresh");
}


$(document).on('pagebeforeshow', '#home', function(event) {
	homepage();
});
