/* Elements cachés par défaut */

$('#options').hide();
$('.postComment').hide();
$('.comments').hide();

/* Permet de redimensionner automatiquement un textarea */

autosize($('textarea'));

/* Permet de fixer le header en haut de la page */

$('.fixedHeader').scrollToFixed();
$('#options').scrollToFixed({
    marginTop: $('.header').outerHeight() + 50
});

/* Listes de hobbies */
$('.tokenizer').select2({
  tags: true,
  tokenSeparators: [',', ' ']
});

$('#tokenizer').change(function() {
  $('#selectedValues').val($('.tokenizer').val());
});

/* Listes de personnes avec image */

$('.multipleTokens').select2({
  templateResult: formatState
});

$('#multipleTokens').change(function() {
  $('#selectedValues').val($(".multipleTokens").val());
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
  $(element).slideUp('slow');
}

function slideOptions (element, vitesse) {
	$(element).slideToggle(vitesse);
	var box_shadow = $('.fixedHeader').css('box-shadow');
	if (box_shadow == 'none') {
		setTimeout(function() {
		      $('.fixedHeader').delay(2000).css('box-shadow', '0px 5px 5px #888888');
		}, 100);
	} else {
		$('#options').css('box-shadow', '0px 5px 5px #888888');
		$('.fixedHeader').css('box-shadow', 'none');
	}
}

function slideElement (element, element2, vitesse) {
  $(element).slideToggle(vitesse);
  if ($(element2).attr('src') == '/EPartage/images/arrow-down.png'){
    $(element2).attr('src', '/EPartage/images/arrow-up.png');
  } else {
    $(element2).attr('src', '/EPartage/images/arrow-down.png');
  }
}

function slideElement2 (element, element2, vitesse) {
  
  if ($(element2).attr('src') == '../images/arrow-down.png'){
    $(element2).attr('src', '../images/arrow-up.png');
    $(element).fadeIn('600');
  } else {
    $(element2).attr('src', '../images/arrow-down.png');
    $(element).fadeOut('600');
  }
}

function formatState (state) {
  if (!state.id) { return state.text; }
  var $state = $(
    '<span><img src="/EPartage/workspace/avatar.htm?id='+state.id+'"/>'+state.text+'</span>'
  );
  return $state;
};