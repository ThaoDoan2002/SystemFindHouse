import React, {useState, useEffect} from 'react';
import { Bar } from "react-chartjs-2";
import { API_URL } from '../../config/APIs';

function Dashboard() {
    const [loading, setLoading] = useState(false);
    const [label, setLabel] = useState([]);
    const [chartData, setChartData] = useState([]);

    useEffect(async () => {
        if(!loading) {
            await fetch(API_URL + "report/year",{ method: 'GET'}).then((response) => {
                if(response.ok){
                    return response.json();
                }
                throw response;
            }).then(data => {
                setLabel(data.name);
                setChartData(data.data);
                setLoading(true);
            }).catch((error) => {
                return error;
            });
        }
    }, []);

    useEffect(async () => {
        if (chartData.length > 0 || label.length > 0) {
            setLoading(true);
        } else {
            setLoading(false);
        }
    }, [label, chartData])

    return (
        <>
            <div id="admin-banner">
                <div className="admin-banner p-2 pl-2 mt-4 mb-2 rounded-3 bold text-white bg-dark text-uppercase">
                    Báo cáo
                </div>
            </div>
            <div className="container pb-5 pt-2 px-0">
                <h3 className="text-center">Thống kê doanh thu 12 tháng gần nhất</h3>
                { loading ?
                    <Bar
                        data={{
                            labels: label,
                            datasets: [
                                {
                                    label: "Doanh thu (VND)",
                                    backgroundColor: [
                                        "#efd6d5",
                                        "#8d8e93",
                                        "#d8d9d3",
                                        "#a5afa9",
                                        "#deb0ae",
                                        "#c0cac2",
                                    ],
                                    data: chartData
                                }
                            ]
                        }}
                        options={{
                            legend: { display: false },
                            title: {
                                display: true,
                                text: "Predicted world population (millions) in 2050"
                            }
                        }}
                    />
                    :
                    <div>Loading...</div>
                }
            </div>
        </>
    )
};

export default Dashboard;