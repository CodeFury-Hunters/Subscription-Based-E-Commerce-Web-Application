document.addEventListener('DOMContentLoaded', () => {
    // Example data
    const personName = 'John Doe'; // Replace with actual person name
    const cartItemCount = 5; // Replace with actual cart item count

    // Update greeting
    const greetingElement = document.getElementById('greeting');
    greetingElement.textContent = `Hello, ${personName}`;

    // Update cart icon
    const cartIconElement = document.getElementById('cart-icon');
    cartIconElement.textContent = cartItemCount;

    // Toggle navigation sidebar
    const navToggle = document.getElementById('nav-toggle');
    const navSidebar = document.getElementById('nav-sidebar');
    const navClose = document.getElementById('nav-close');

    navToggle.addEventListener('click', () => {
        navSidebar.classList.toggle('open');
    });

    navClose.addEventListener('click', () => {
        navSidebar.classList.remove('open');
    });
});
