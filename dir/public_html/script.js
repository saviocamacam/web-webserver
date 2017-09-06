function parametro(botao, modalName) {
    var dialog = document.querySelector(modalName);
    var closeButton = dialog.querySelector('button');
            
    if (! dialog.showModal) {
        dialogPolyfill.registerDialog(dialog);
    }
    var closeClickHandler = function(event) {
        closeButton.removeEventListener('click', closeClickHandler);
        dialog.close();
    };
    var showClickHandler = function() {
        dialog.showModal();
    };
    closeButton.addEventListener('click', closeClickHandler);
    showClickHandler();
}