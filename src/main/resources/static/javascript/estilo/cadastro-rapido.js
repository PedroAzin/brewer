$(function() {
	var modal = $('#modalCadastroRapido')
	var form = modal.find('form');
	var url = form.attr('action')
	var campoNomeEstilo = $('#estilo-nome')
	var botaoSaveEstilo = $('#btn-save-estilo')
	var inputNomeEstilo = $('#estilo-nome')
	var containerMensagemErro = $('.js-mensagem-cadastro-rapido-estilo');

	modal.on('shown.bs.modal', onModalShow);
	modal.on('hide.bs.modal', onModalClose)

	function onModalShow() {
		inputNomeEstilo.focus();
	}

	function onModalClose() {
		inputNomeEstilo.val('');
		containerMensagemErro.addClass('hidden');
		form.find('.form-group').removeClass('has-error');
	}

	form.on('submit', function(e) {
		e.preventDefault()
	})

	botaoSaveEstilo.on('click', function(e) {
		$.ajax({
			type : "POST",
			url : url,
			data : JSON.stringify({
				nome : inputNomeEstilo.val()
			}),
			success : onEstiloSalvo,
			error : onErroSalvandoEstilo,
			contentType : 'application/json'
		});
	})

	function onErroSalvandoEstilo(obj) {
		var mensagemErro = obj.responseText;
		containerMensagemErro.removeClass('hidden');
		containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		form.find('.form-group').addClass('has-error');
	}

	function onEstiloSalvo(estilo) {
		var comboEstilo = $('#estilo');
		comboEstilo.append('<option value=' + estilo.codigo + '>' + estilo.nome
				+ '</option>');
		comboEstilo.val(estilo.codigo);
		modal.modal('hide');
	}

})