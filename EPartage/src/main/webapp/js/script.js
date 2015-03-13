$("#options").hide();
$(".postComment").hide();
$(".comments").hide();

autosize($('textarea'));

$('.fixedHeader').scrollToFixed();
$('#options').scrollToFixed({
    marginTop: $('.header').outerHeight() + 50
});

$(".tokenizer").select2({
  tags: true,
  tokenSeparators: [',', ' ']
})

$(".multiple-tokens").select2({
  templateResult: formatState
});

$('#selector').change(function() {
  $('#selectedValues').val($(".tokenizer").val());
});

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

function formatState (state) {
  if (!state.id) { return state.text; }
  var $state = $(
    '<span><img src="../Images/logo.png" /> ' + state.text + '</span>'
  );
  return $state;
};