import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.jsx";
import { createBrowserRouter, RouterProvider, createRoutesFromElements, Route } from "react-router-dom";
import About from "./components/About.jsx";
import Contact, {contactAction} from "./components/Contact.jsx";
import Cart from "./components/Cart.jsx";
import Login from "./components/Login.jsx";
import Home, {productsLoader} from "./components/Home.jsx";
import ErrorPage from "./components/ErrorPage.jsx";
import ProductDetail from "./components/ProductDetail.jsx";
import { ToastContainer, Bounce } from "react-toastify";
import "react-toastify/ReactToastify.css"
import { CartProvider } from "./store/cart-context.jsx";

const routeDefinition = createRoutesFromElements(
  <Route path="/" element={<App />} errorElement = {<ErrorPage />}>
    <Route index element={<Home />} loader={productsLoader}/>
    <Route path="/home" element={<Home />} loader={productsLoader}/>
    <Route path="/about" element={<About />} />
    <Route path="/contact" element={<Contact />} action={contactAction}/>
    <Route path="/cart" element={<Cart />} />
    <Route path="/login" element={<Login />} />
    <Route path="/product/:productId" element={<ProductDetail />}/>
  </Route>
);


const appRouter = createBrowserRouter( 
  routeDefinition
);


createRoot(document.getElementById("root")).render(
  <StrictMode>
    <CartProvider>
      <RouterProvider router={appRouter} />
    </CartProvider>

    <ToastContainer 
    position="top-center"
    autoClose={2000}
    hideProgressBar={false}
    newestOnTop={false}
    draggable
    pauseOnHover
    theme={localStorage.getItem("theme") === "dark" ? "dark" : "light"}
    transition={Bounce}
    />
  </StrictMode>
);
