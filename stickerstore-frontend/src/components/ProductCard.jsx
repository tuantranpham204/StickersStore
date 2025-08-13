import React, { act, useContext } from "react";
import Price from "./Price";
import { Link } from "react-router-dom";
import { CartContext } from "../store/cart-context";

export default function ProductCard({ product }) {
  const { addToCart } = useContext(CartContext)

  return (
    <Link state={{product}} to={`/product/${product.productId}`} className="w-72 rounded-md mx-auto border border-gray-300 shadow-md overflow-hidden flex flex-col bg-white transition dark:border-gray-600 hover:border-primary dark:hover:border-lighter">
      <div className="relative w-full h-72 border-b border-gray-300 dark:border-gray-600">
        <img
          src={product.productImageUrl}
          alt={product.productName}
          className="w-full h-full object-cover transition-transform duration-500 ease-in-out hover:scale-110"
        />
      </div>
      <div className="relative h-48 p-4 flex flex-col font-primary bg-normalbg dark:bg-darkbg">
        <h2 className="text-xl font-semibold text-primary mb-2 dark:text-light">
          {product.productName}
        </h2>
        <p className="text-base text-gray-600 mb-4 dark:text-lighter">{product.productDescription}</p>
        <div className="flex items-center justify-between mt-auto">
          <div className="bg-lighter text-primary font-medium text-sm py-2 px-4 rounded-tl-md dark:text-light dark:bg-dark">
            <Price currency="$" price={product.productUnitPrice} />
          </div>
          <button
            className="bg-primary dark:bg-light text-white dark:text-primary font-medium text-sm py-2 px-4 rounded-md hover:cursor-pointer"
            onClick={addToCart}
          >
            Add to Cart
          </button>
        </div>
      </div>
    </Link>
  );
}
