$(document).ready(function() {
    $('.recipe-saved-text').text("Save this recipe");

    }

);

var url;

function like(){
    //check if original state was liked or not
  //  var status = isLiked();
    //Toggle glyphicons to display opposite
    $('.recipe-favourite').toggleClass('glyphicon-heart-empty');
    $('.recipe-favourite').toggleClass('glyphicon-heart');
/*
    //Depending on what original status was
    //call appropriate function
    if(status) {
        $('.recipe-saved-text').text("Saved");
        url = "/save-recipe";

    } else {
        $('.recipe-saved-text').text("Save this recipe");
        url = "/remove-recipe";
    }
    console.log("here");
    doSomething();
*/
};
/*
function isLiked() {
    var classList = $('.recipe-favourite').attr('class').split(/\s+/);
    var isLiked = false;
    $.each(classList, function(index, item) {
        if (item === 'glyphicon-heart-empty') {
            isLiked = true;
        }
    });
    return isLiked;
};



*/