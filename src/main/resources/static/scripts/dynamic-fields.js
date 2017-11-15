$(document).ready(function() {

    $(".add-more-instruction").click(function(){
        var html = $(".copy-instruction").html();
        $(".after-add-more-instruction").after(html);
    });

    $(".add-more-ingredient").click(function(){
       var html = $(".copy-ingredient").html();
      $(".after-add-more-ingredient").after(html);


    });

    $("body").on("click",".remove",function(){
        $(this).parents(".control-group").remove();
    });

});


