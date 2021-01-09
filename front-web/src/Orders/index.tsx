import { useEffect, useState } from 'react';
import './styles.css';
import StepsHeader from './StepsHeader';
import ProductsList from './ProductsList';
import { OrderLocationdata, Product } from './types';
import { fetchProducts } from '../api';
import OrderLocation from './OrderLocation';


function Orders(){
    const[products, SetProducts] = useState<Product[]>([]);
    const[orderLocation, SetOrderLocation] = useState<OrderLocationdata>() ;

    useEffect(() => {
       fetchProducts()
       .then(response => SetProducts(response.data))
       .catch(error => console.log(error))
     }, []); 

    return(
        <div className="orders-container">
           <StepsHeader/>
           <ProductsList products={products}/>
           <OrderLocation onChangeLocation={location => SetOrderLocation(location)}/>
        </div>
    )
}

export default Orders;