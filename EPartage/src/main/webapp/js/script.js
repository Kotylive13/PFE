$("#options").hide();
$(".postComment").hide();
$(".comments").hide();

autosize($('textarea'));

$('.fixedHeader').scrollToFixed();
$('#options').scrollToFixed({
    marginTop: $('.header').outerHeight() + 50
});

var ias = $.ias({
  container:  "#publications",
  item:       ".publication",
  pagination: "#pagination",
  next:       ".next"
});

ias.extension(new IASSpinnerExtension());            // shows a spinner (a.k.a. loader)
ias.extension(new IASTriggerExtension({offset: 3})); // shows a trigger after page 3
ias.extension(new IASNoneLeftExtension({
  text: 'There are no more pages left to load.'      // override text when no pages left
}));

function hover(element, src) {
    element.setAttribute('src', src);
}

function unhover(element, src) {
    element.setAttribute('src', src);
}

function slideToggle(element, vitesse) {
  $(element).slideToggle(vitesse);
}

function slideUp(element) {
  $(element).slideUp("slow");
}

function remove(element) {
  $(element).slideUp("slow");
}

function slideOptions (element, vitesse) {
	$(element).slideToggle(vitesse);
	var box_shadow = $('.fixedHeader').css("box-shadow");
	if (box_shadow == "none") {
		setTimeout(function() {
		      $('.fixedHeader').delay(2000).css("box-shadow", "0px 5px 5px #888888");
		}, 100);
	} else {
		$('#options').css("box-shadow", "0px 5px 5px #888888");
		$('.fixedHeader').css("box-shadow", "none");
	}
}