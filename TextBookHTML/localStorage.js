function submit() {
	localStorage.removeItem("objects");
	// Retrieve the entered form data
	var title = $('[name="title"]').val();
	var author = $('[name="author"]').val();
	// Fetch the existing objects
	var objects = getObjects();
	// Push the new item into the existing list
	objects.push({
		title : title,
		author : author
	});
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

function showBooksPage() {
	// Fetch the existing objects
	objects = getObjects();
	// Clear the list
	//$('#items').find('li').remove();
	// Add every object to the objects list
	$.each(objects, function(index, item) {
		element = '<li>' + item.title + " - " + item.author + '</li>';
		$('#items').append(element);
	});

	$('#items').listview();
	$('#items').listview("refresh");
}


$(document).on('pagebeforeshow', '#books', function(event) {
	showBooksPage();
});
