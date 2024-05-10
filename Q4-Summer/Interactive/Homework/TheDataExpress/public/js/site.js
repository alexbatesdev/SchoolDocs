const ctx1 = document.getElementById('myChart1').getContext('2d');
const ctx2 = document.getElementById('myChart2').getContext('2d');
const ctx3 = document.getElementById('myChart3').getContext('2d');

const url = "http://localhost:3000/api/getChartData";

fetch(url)
    .then(r => r.json())
    .then(data => {
        SetupChart(data.questions[0][0], data.questions[0][1].labels, data.questions[0][1].data, ctx1);
        SetupChart(data.questions[1][0], data.questions[1][1].labels, data.questions[1][1].data, ctx2);
        SetupChart(data.questions[2][0], data.questions[2][1].labels, data.questions[2][1].data, ctx3);
    })

function SetupChart(title, labels, data, context) {
    const myChart = new Chart(context, {
        type: 'pie',
        data: {
            labels: labels,
            datasets: [{
                label: title,
                data: data,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}