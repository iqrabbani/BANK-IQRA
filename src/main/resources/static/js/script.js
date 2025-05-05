   // Script untuk navbar toggle di perangkat mobile
   const navbarToggle = document.getElementById('navbar-toggle');
   const navbarMenu = document.querySelector('.navbar-menu');

   navbarToggle.addEventListener('click', () => {
       navbarMenu.classList.toggle('active');
   });
