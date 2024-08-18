document.addEventListener('DOMContentLoaded', () => {
    const cartItems = JSON.parse(localStorage.getItem('cart')) || [];
    renderOrderSummary(cartItems);
});

function renderOrderSummary(cartItems) {
    const orderItemContainer = document.getElementById('order-item');
    let subtotal = 0;

    orderItemContainer.innerHTML = ''; // Clear previous content

    cartItems.forEach(item => {
        const itemTotal = (item.price * item.quantity).toFixed(2);
        subtotal += item.price * item.quantity;

        orderItemContainer.innerHTML += `
            <div class="order-item-detail">
                <div class="order-item-image">
                    <img src="${item.image}" alt="${item.name}">
                </div>
                <div class="order-item-info">
                    <p class="order-item-name">${item.name}</p>
                    <p class="order-item-qty">Quantity: ${item.quantity}</p>
                    <p class="order-item-total">Total: Rs. ${itemTotal}</p>
                </div>
            </div>
        `;
    });


    const gst = 0.05 * subtotal; // 5% GST
    const delivery = 50; // Example flat delivery charge
    const grandTotal = subtotal + gst + delivery;

    document.getElementById('subtotal').textContent = subtotal.toFixed(2);
    document.getElementById('gst').textContent = gst.toFixed(2);
    document.getElementById('delivery').textContent = delivery.toFixed(2);
    document.getElementById('grandTotal').textContent = grandTotal.toFixed(2);
}

function proceedToPayment() {
    const subtotal = parseFloat(document.getElementById('subtotal').textContent);
    const gst = parseFloat(document.getElementById('gst').textContent);
    const delivery = parseFloat(document.getElementById('delivery').textContent);
    const grandTotal = parseFloat(document.getElementById('grandTotal').textContent);

    // Save totals to Local Storage before redirecting
    localStorage.setItem('checkoutSummary', JSON.stringify({ subtotal, gst, delivery, grandTotal }));

    // Redirect to payment page
    window.location.href = 'payment.html';
}
