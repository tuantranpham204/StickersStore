import { useState, useEffect } from "react";
import apiClient from "../api/apiClient";
// import products from "../data/products";
import PageHeading from "./PageHeading";
import ProductListings from "./ProductListings";
import { useLoaderData } from "react-router-dom";

export default function Home() {
  // const [products, setProducts] = useState([])
  // const [loading, setLoading] = useState(true)
  // const [error, setError] = useState(false)


  // useEffect(() => {
  //   fetchProducts()
  // }, [])
  
  // const fetchProducts = async() => {
    //   try {
    //   setLoading(true)
    //   const response = await apiClient.get("/products")
    //   setProducts(response.data.data)
    // } catch (error) {
    //   // setError(error.response?.data?.message || "Failed to fetch products. Try again")
    // } finally {
    //   // setLoading(false)
    // }
  // };

  // if (loading) {
  //   return (
  //     <div className="flex items-center justify-center min-h-screen">
  //       <span className="text-xl font-semibold">Loading products...</span>
  //     </div>
  //   );
  // }

  // if (error) {
  //   return (
  //     <div className="flex items-center justify-center min-h-screen">
  //       <span className="text-xl text-red-500">Error: {error}</span>
  //     </div>
  //   );
  // }

  const products = useLoaderData();
  
  
  return (
    <div className="max-w-[1152px] mx-auto px-6 py-8">
      <PageHeading title="Explore Sticker Store!">
        Add a touch of creativity to your space with our wide range of fun and
        unique stickers. Perfect for any occasion!
      </PageHeading>
      <ProductListings 
        products={products} 
      />
    </div>
  );
};

export async function productsLoader() {
  // const [loading, setLoading] = useState(true)


  try {
      // setLoading(true)
      const response = await apiClient.get("/products")
      return response.data.data
    } catch (error) {
      // setError(error.response?.data?.message || "Failed to fetch products. Try again")
      throw new Response(error.message || "Failed to fetch products. ",
        {status:error.status || 500}
      )
    } 
    // finally {
    //   // setLoading(false)
    // }
};
