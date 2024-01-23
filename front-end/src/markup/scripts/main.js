const constactIcons = document.querySelectorAll('.feedback-button__icon');

const ContactsIcons = {
  pressed: false,

  toggle() {
    [...constactIcons].forEach(elem => elem.classList.toggle('feedback-button__icon--show'));

    this.pressed = !this.pressed;
  },

  handleDocumentClick: function(event) {
    const menuIconBtn = document.querySelector('.feedback-button__icon-message');
    const targetElement = event.target;
  
    if (this.pressed && targetElement !== menuIconBtn && !menuIconBtn.contains(targetElement)) {
      this.toggle();
    }
  }
}

document.addEventListener('click', ContactsIcons.handleDocumentClick.bind(ContactsIcons));




