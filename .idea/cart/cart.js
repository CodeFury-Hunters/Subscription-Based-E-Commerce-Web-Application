// Function to render the cart items
function renderCart() {
    // Retrieve cart items from Local Storage
    let cartItems = JSON.parse(localStorage.getItem('cart')) || [];

    const cartContainer = document.querySelector('.cart-items-container');
    cartContainer.innerHTML = ''; // Clear the container before re-rendering

    cartItems.forEach((item, index) => {
        const itemTotal = (item.price * item.quantity).toFixed(2);

        cartContainer.innerHTML += `
            <div class="cart-item">
                <img src="${item.image}" alt="${item.name}">
                <div class="item-details">
                    <h2>${item.name}</h2>
                    <p>Price: Rs.<span class="item-price">${item.price.toFixed(2)}</span></p>
                    <div class="quantity-selector">
                        <button class="quantity-btn" data-action="minus" data-index="${index}">-</button>
                        <span class="quantity">${item.quantity}</span>
                        <button class="quantity-btn" data-action="plus" data-index="${index}">+</button>
                    </div>
                </div>
                <div class="item-total">
                    Rs.<span>${itemTotal}</span>
                </div>
                <button class="remove-item-btn" data-index="${index}">Remove</button>
            </div>
        `;
    });

    updateCartSummary();
}

// Function to update the quantity of an item
function updateQuantity(action, index) {
    let cartItems = JSON.parse(localStorage.getItem('cart')) || [];

    if (action === 'plus') {
        cartItems[index].quantity++;
    } else if (action === 'minus') {
        if (cartItems[index].quantity > 1) {
            cartItems[index].quantity--;
        }
    }

    // Save the updated cart back to Local Storage
    localStorage.setItem('cart', JSON.stringify(cartItems));

    renderCart();
}

// Function to remove an item from the cart
function removeItem(index) {
    let cartItems = JSON.parse(localStorage.getItem('cart')) || [];
    cartItems.splice(index, 1);

    // Save the updated cart back to Local Storage
    localStorage.setItem('cart', JSON.stringify(cartItems));

    renderCart();
}
// Function to apply a promo code
function applyPromoCode() {
    const promoCodeInput = document.getElementById('promo-code-input').value.trim();
    let discount = 0;
    
    if (promoCodeInput === 'SAVE10') {
        discount = 10; // Rs. 10 flat discount for demonstration
        alert('Promo code applied successfully!');
    } else {
        alert('Invalid promo code.');
    }

    localStorage.setItem('discount', discount);
    updateCartSummary();
}

// Updated function to update the cart summary with discount
function updateCartSummary() {
    let cartItems = JSON.parse(localStorage.getItem('cart')) || [];
    const subtotal = cartItems.reduce((sum, item) => sum + (item.price * item.quantity), 0).toFixed(2);
    const deliveryCharges = 50.00; // Example flat delivery charge
    const discount = parseFloat(localStorage.getItem('discount')) || 0.00;
    const total = (parseFloat(subtotal) + deliveryCharges - discount).toFixed(2);

    document.getElementById('subtotal').textContent = subtotal;
    document.getElementById('delivery-charges').textContent = deliveryCharges.toFixed(2);
    document.getElementById('discount').textContent = discount.toFixed(2);
    document.getElementById('total').textContent = total;
}




// Function to proceed to the checkout page
function proceedToCheckout() {
    window.location.href = 'checkout.html';
}

// Initial render
renderCart();

// Event delegation for quantity buttons and remove buttons
document.querySelector('.cart-items-container').addEventListener('click', function(event) {
    if (event.target.matches('.quantity-btn')) {
        const action = event.target.getAttribute('data-action');
        const index = parseInt(event.target.getAttribute('data-index'), 10);
        updateQuantity(action, index);
    } else if (event.target.matches('.remove-item-btn')) {
        const index = parseInt(event.target.getAttribute('data-index'), 10);
        removeItem(index);
    }
});
