$(document).ready(function() {
        //Display 'All' if search is empty
    var input = $('#searchresult').text();
        if(input == ''){
            $('#searchresult').text("All");

        }
});