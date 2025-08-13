import React, { useMemo, useState } from "react";
import ProductCard from "./ProductCard";
import SearchBox from "./SearchBox";
import Dropdown from "./Dropdown";

const sortList = ["Popularity", "Price Low to High", "Price High to Low"];

export default function ProductListings({ products }) {
  const [searchText, setSearchText] = useState("");
  const [selectedSort, setSelectedSort] = useState(sortList[0]);

  const filteredAndSortedProducts = useMemo(() => {
    if (!Array.isArray(products)) return [];

    let filteredProducts = products.filter(
      (product) =>
        product.productName.toLowerCase().includes(searchText.toLowerCase()) ||
        product.productDescription
          .toLowerCase()
          .includes(searchText.toLowerCase())
    );

    return filteredProducts.slice().sort((a, b) => {
      switch (selectedSort) {
        case sortList[1]:
          return parseFloat(a.productPrice) - parseFloat(b.productPrice);
        case sortList[2]:
          return parseFloat(b.productPrice) - parseFloat(a.productPrice);
        case sortList[0]:
        default:
          return (
            parseFloat(b.productPopularity) - parseFloat(a.productPopularity)
          );
      }
    });
  }, [products, searchText, selectedSort]);

  function handleSearchChange(inputSearch) {
    setSearchText(inputSearch);
  }

  function handleSortChange(sortType) {
    setSelectedSort(sortType);
  }

  // let filterAndSortedProducts = Array.isArray(products)
  //   ? products.filter((product) => {
  //       return (
  //         product.productName
  //           .toLowerCase()
  //           .includes(searchText.toLowerCase()) ||
  //         product.productDescription
  //           .toLowerCase()
  //           .includes(searchText.toLowerCase())
  //       );
  //     })
  //   : [];

  // switch (selectedSort) {
  //   case sortList[1]:
  //     filterAndSortedProducts = filterAndSortedProducts.sort(
  //       (a, b) => parseFloat(a.productPrice) - parseFloat(b.productPrice)
  //     );
  //     break;
  //   case sortList[2]:
  //     filterAndSortedProducts = filterAndSortedProducts.sort(
  //       (a, b) => parseFloat(b.productPrice) - parseFloat(a.productPrice)
  //     );
  //     break;
  //   case sortList[0]:
  //   default:
  //     filterAndSortedProducts = filterAndSortedProducts.sort(
  //       (a, b) => parseInt(b.productPopularity) - parseInt(a.productPopularity)
  //     );

  //     break;
  // }

  return (
    <div className="max-w-[1152px] mx-auto">
      <div className="flex flex-col sm:flex-row justify-between items-center gap-4 pt-12">
        <SearchBox
          label="Search"
          placeholder="Search products..."
          value={searchText}
          handleSearch={handleSearchChange}
        />
        <Dropdown
          label="Sort by"
          options={sortList}
          value={selectedSort}
          handleSort={handleSortChange}
        />
      </div>
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-y-8 gap-x-6 py-12">
        {filteredAndSortedProducts.length > 0 ? (
          filteredAndSortedProducts.map((product) => (
            <ProductCard key={product.productId} product={product} />
          ))
        ) : (
          <p className="text-center font-primary font-bold text-lg text-primary">
            No products found
          </p>
        )}

        {/* {products.length > 0 ? (
          products.map((product) => (
            <ProductCard key={product.productId} product={product} />
          ))
        ) : (
          <p className="text-center font-primary font-bold text-lg text-primary">
            No products found
          </p>
        )} */}
      </div>
    </div>
  );
}
