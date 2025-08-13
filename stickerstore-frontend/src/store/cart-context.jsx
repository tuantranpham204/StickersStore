import {createContext, Provider, useState} from "react"

// const initialCartContext = {
//   cart: [],
//   setCart: () => {},
//   removeFromCart:() => {},
//   addToCart:() => {},
//   totalQuantity: 0
// }

export const CartContext = createContext();

export const CartProvider = ({children}) => {
  const [cart, setCart] = useState(() => {
    try {
      const storedCart = localStorage.getItem("cart")
      return storedCart ? JSON.parse(storedCart) : []
    } catch (error) {
      console.error("Failed to parse cart from local storage:", error)
      return []
    }
  });

  useEffect(() => {
    try {
      localStorage.setItem("cart", JSON.stringify(cart));
    } catch (error) {
      console.error("Failed to save cart to localStorage:", error);
    }
  }, [cart]);
  
  const addToCart = (product, quantity) => {
    setCart((prevCart) => {
      const existingItem = prevCart.find(
        (item) => item.productId === product.productId
      );

      if (existingItem) {
        // Use map() to create a new array with updated quantity
        return prevCart.map((item) =>
          item.productId === product.productId
            ? { ...item, subQuantity: item.subQuantity + quantity }
            : item
        );
      }

      // If the product is not in the cart, add it
      return [...prevCart, { ...product, subuantity }];
    });
  };

  // Function to remove an item from the cart
  const removeFromCart = (productId) => {
    setCart((prevCart) =>
      prevCart.filter((item) => item.productId !== productId)
    );
  };

  // Calculate total quantity
  const totalQuantity = cart.reduce((acc, item) => acc + item.subQuantity,0)


    return  <CartContext.Provider value={{cart, setCart, addToCart, removeFromCart, totalQuantity}}>
        {children}
    </CartContext.Provider>;
}