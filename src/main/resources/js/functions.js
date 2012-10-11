$(document).ready(function() {
	$(window).hashchange(onHashChange);
	$(window).hashchange(); // Call this to init the path after a site reload or calling from favorites
});

Flo = new Object();
Flo.nav = new Object();

Flo.event = new Object();

Flo.pulldispatch = new Object();
Flo.pulldispatch.nextId = 0;

function registerEvent(eventType, callback) {
	if (Flo.event[eventType] == undefined) {
		Flo.event[eventType] = new Array();
	}
	Flo.event[eventType].push(callback);
}

function callEvent(eventType, data) {
	if (Flo.event[eventType] != undefined) {
		$(Flo.event[eventType]).each(function() {
			this(data);
		});
	}
}

function pullData(requestType, arguments, callback) {
	var next = Flo.pulldispatch.nextId ++;
	
	var request = new Object();
	request.requestType = requestType;
	request.args = arguments;
	request.requestId = next;
	
	Flo.pulldispatch[next] = callback;
	
	Flo.websocket.send(JSON.stringify(request));
}

Flo.websocket = new WebSocket("ws://localhost:5000/websocket/");
Flo.websocket.onopen = function(evt) {
	// Handle open
}

Flo.websocket.onclose = function(evt) {
	
}

Flo.websocket.onmessage = function(evt) {
	var data = JSON.parse(evt.data);
	
	if (data.frameType == "push") {
		var eventType = data.eventType;
		var eventData = data.data;
		
		callEvent(eventType, eventData);
	} else if (data.frameType == "pull") {
		var requestId = data.requestId;
		var requestData = data.data;
		
		if (pulldispatch[requestId] != undefined) {
			pulldispatch[requestId](data);
			delete pulldispatch[requestId];
		}
	}
}

Flo.websocket.onerror = function(evt) {
	
}

function onHashChange() {
	var split = window.location.hash.replace("#","").split("/");
	
	// The public nav property contains the handler methods that are called when a navigation occurs
	searchNav(Flo.nav, split);
}

function searchNav(nav, split) {
	if (nav[split[0]] != undefined) {
		searchNav(nav[split[0]], split.slice(1));
	} else {
		if (nav.exec != undefined) {
			nav.exec(split);
		} else {
			return;
		}
	}
}

function loadContent(url, callback) {
	$("#content").load(url, callback);
}

function navHome(args) {
	loadContent("sites/home.html", undefined);
	Flo.nav.bar.setActive("#");
}

Flo.nav.bar = new Object();
Flo.nav.bar.setActive = function(element) {
	$("#root-navigation .active").removeClass("active");
	$("#root-navigation [href=\""+element+"\"]").parent().addClass("active");
}

Flo.nav.exec = navHome;
Flo.nav.home = new Object();
Flo.nav.home.exec = navHome;

Flo.nav.servers = new Object();
Flo.nav.servers.exec = function(args) {
	loadContent("sites/servers.html");
	Flo.nav.bar.setActive("#servers/");
}

/* **** Test for the navigation delegates ****
Flo.nav.test = new Object();
Flo.nav.test.exec = function(args) {
	alert("TESTING!!! " + args);
}

Flo.nav.test.foo = new Object();
Flo.nav.test.foo.exec = function(args) {
	alert("FOO " + args);
}*/

/* TEST FOR EVENTS */

//registerEvent("counter", eventCounter);
//
//function eventCounter(count) {
//	$("#debug").append("<br/>"+count);
//}