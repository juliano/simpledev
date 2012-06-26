$('#estados').change(function() {
    var estado = $('#estados').val();
    $.getJSON('cidades', {'estado' : estado}, function(json) {
    	var options = '';
    	$(json.cidades).each(function(index, cidade) {
    		options += '<option value="' + cidade + '">' + cidade + '</option>';
    	})

    	$("#cidades").html(options);
    });
});