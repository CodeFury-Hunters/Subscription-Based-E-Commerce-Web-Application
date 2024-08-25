document.addEventListener('DOMContentLoaded', () => {
    const checkoutSummary = JSON.parse(localStorage.getItem('checkoutSummary')) || {};
    const cartItems = JSON.parse(localStorage.getItem('cart')) || [];

    if (Object.keys(checkoutSummary).length === 0 || cartItems.length === 0) {
        document.querySelector('#order-item').innerHTML = '<p>Your cart is empty.</p>';
        return;
    }

    renderOrderSummary(cartItems, checkoutSummary);
});

function renderOrderSummary(cartItems, checkoutSummary) {
    const orderItemContainer = document.querySelector('#order-item');
    const summaryDetailsContainer = document.querySelector('#summary-details');
    let subtotal = 0;

    orderItemContainer.innerHTML = ''; // Clear previous content

    cartItems.forEach(item => {
        const itemTotal = (item.price * item.quantity).toFixed(2);
        subtotal += item.price * item.quantity;

        orderItemContainer.innerHTML += `
            <div class="order-item-detail" style="display: flex; align-items: center; margin-bottom: 15px;">
                <img src="${item.image}" alt="${item.name}" style="width: 100px; height: auto; border-radius: 5px; margin-right: 15px;">
                <div class="order-item-info" style="display: flex; flex-direction: column;">
                    <p class="order-item-name" style="font-weight: bold; margin: 0;">${item.name}</p>
                    <p class="order-item-qty" style="margin: 5px 0;">Quantity: ${item.quantity}</p>
                    <p class="order-item-total" style="margin: 0;">Total: Rs. ${itemTotal}</p>
                </div>
            </div>
        `;
    });

    const { gst, delivery, grandTotal } = checkoutSummary;

    summaryDetailsContainer.innerHTML = `
        <p>Subtotal: Rs. <span id="subtotal">${checkoutSummary.subtotal.toFixed(2)}</span></p>
        <p>GST: Rs. <span id="gst">${gst.toFixed(2)}</span></p>
        <p>Delivery: Rs. <span id="delivery">${delivery.toFixed(2)}</span></p>
        <hr>
        <h3>Grand Total: Rs. <span id="grandTotal">${grandTotal.toFixed(2)}</span></h3>
    `;
}

document.getElementById('payment-form').addEventListener('submit', function(event) {
    event.preventDefault();

    // Gather form data
    const formData = {
        name: document.getElementById('name').value,
        cardNumber: document.getElementById('card-number').value,
        expiryDate: document.getElementById('expiry-date').value,
        cvv: document.getElementById('cvv').value,
       // billingAddress: document.getElementById('billing-address').value,
       // email: document.getElementById('email').value,
       // phone: document.getElementById('phone').value
    };

    // Perform basic validation
    if (!formData.name || !formData.cardNumber || !formData.expiryDate || !formData.cvv || !formData.billingAddress || !formData.email || !formData.phone) {
        alert('Please fill in all required fields.');
        return;
    }

    // Handle form submission (e.g., send data to server)
    alert('Payment successful!');
});
