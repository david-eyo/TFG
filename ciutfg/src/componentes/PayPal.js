import React, { useRef, useEffect } from "react";

export default function PayPal({cantidad, realizarPedido, setError}){

    const paypal = useRef();

    useEffect(()=>{
        window.paypal.Buttons({
            createOrder: (data, actions, err) => {
                return actions.order.create({
                    intent: "CAPTURE",
                    purchase_units: [
                        {
                            description: "Prueba de compra",
                            amount: {
                                currency_code: "EUR",
                                value: cantidad,
                            }
                        }
                    ]
                })
            },
            onApprove: async (data, actions) => {
                const order = await actions.order.capture();
                realizarPedido();
            },
            onError: (err) =>{
                setError(err);
            }
        }).render(paypal.current)
    },[])
    return (
        <div>
            <div ref={paypal}></div>
        </div>
    )
}