// Dummy data
const dashboardData = {
    dailyCustomers: 120,
    activeSubscriptions: 85,
    monthlyProfit: 15000,
    monthlyLosses: 5000,
    customersRetained: 30
};

// Populate dashboard cards with data
document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('daily-customers').textContent = dashboardData.dailyCustomers;
    document.getElementById('active-subscriptions').textContent = dashboardData.activeSubscriptions;
    document.getElementById('monthly-profit').textContent = `$${dashboardData.monthlyProfit}`;
    document.getElementById('monthly-losses').textContent = `$${dashboardData.monthlyLosses}`;
    document.getElementById('customers-retained').textContent = dashboardData.customersRetained;

    // Render charts
    renderCharts();
});

function renderCharts() {
    // Example chart rendering using Chart.js (or any other chart library)
    const ctxProfitLoss = document.getElementById('profit-loss-chart').getContext('2d');
    new Chart(ctxProfitLoss, {
        type: 'pie',
        data: {
            labels: ['Profit', 'Losses'],
            datasets: [{
                data: [dashboardData.monthlyProfit, dashboardData.monthlyLosses],
                backgroundColor: ['#FF8225', '#B43F3F']
            }]
        }
    });

    const ctxSubscriptions = document.getElementById('subscriptions-chart').getContext('2d');
    new Chart(ctxSubscriptions, {
        type: 'bar',
        data: {
            labels: ['Active Subscriptions'],
            datasets: [{
                label: 'Subscriptions',
                data: [dashboardData.activeSubscriptions],
                backgroundColor: '#FF8225'
            }]
        }
    });
}
// Example of a Pie Chart
const ctxPie = document.getElementById('myPieChart').getContext('2d');
const myPieChart = new Chart(ctxPie, {
  type: 'pie',
  data: {
    labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
    datasets: [{
      data: [12, 19, 3, 5, 2, 3],
      backgroundColor: ['red', 'blue', 'yellow', 'green', 'purple', 'orange'],
    }]
  },
  options: {
    responsive: true,
    plugins: {
      legend: {
        position: 'top',
      },
      tooltip: {
        callbacks: {
          label: function(tooltipItem) {
            return tooltipItem.label + ': ' + tooltipItem.raw;
          }
        }
      }
    }
  }
});

// Example of a Line Chart
const ctxLine = document.getElementById('myLineChart').getContext('2d');
const myLineChart = new Chart(ctxLine, {
  type: 'line',
  data: {
    labels: ['January', 'February', 'March', 'April', 'May', 'June'],
    datasets: [{
      label: 'Monthly Sales',
      data: [10, 20, 30, 40, 50, 60],
      borderColor: 'rgba(75, 192, 192, 1)',
      backgroundColor: 'rgba(75, 192, 192, 0.2)',
      fill: true,
    }]
  },
  options: {
    responsive: true,
    plugins: {
      legend: {
        position: 'top',
      },
      tooltip: {
        callbacks: {
          label: function(tooltipItem) {
            return tooltipItem.label + ': ' + tooltipItem.raw;
          }
        }
      }
    }
  }
});


// Example of dynamically updating chart data
function updateChartData(chart, newData) {
    chart.data.datasets[0].data = newData;
    chart.update();
  }
  
  // Example of fetching data and updating the chart
  fetch('api/endpoint')
    .then(response => response.json())
    .then(data => {
      updateChartData(myPieChart, data.pieChartData);
      updateChartData(myLineChart, data.lineChartData);
    });
    const sidebar = document.getElementById('sidebar');
    const overlay = document.getElementById('overlay');
    const toggleButton = document.getElementById('sidebar-toggle');
    
    toggleButton.addEventListener('click', () => {
      sidebar.classList.toggle('show');
      overlay.classList.toggle('show');
    });
    
    overlay.addEventListener('click', () => {
      sidebar.classList.remove('show');
      overlay.classList.remove('show');
    });
    
    function toggleSidebar() {
        const sidebar = document.getElementById('sidebar');
        const overlay = document.getElementById('overlay');
        sidebar.classList.toggle('show');
        overlay.classList.toggle('show');
    }
    
    // Function to toggle the Add Item Popup
    function toggleAddItemPopup() {
        const popup = document.getElementById('add-item-popup');
        popup.classList.toggle('show');
    }
    
    // Load header, sidebar, and footer
    document.addEventListener('DOMContentLoaded', () => {
        loadHTML('header.html', 'header-container');
        loadHTML('sidebar.html', 'sidebar-container');
        loadHTML('footer.html', 'footer-container');
    });