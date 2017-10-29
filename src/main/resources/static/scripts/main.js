$(document).ready(function() {
    $('.main-header').hide();
    $('.main-header').fadeIn(5000);
});

$('#search').submit(function(){
    var input = $('#search-input').val();
    if(input == ''){
        $('#searchresult').innerHTML='All';
    }
});