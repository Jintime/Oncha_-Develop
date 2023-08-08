import {get, post, del} from "../util/commonDataInitializer.js";

/**
 * 자신의 장바구니 목록 호출
 * @param {number} pageNumber
 * @param {number} pageSize
 * @returns {Promise<{}>}
 */
export async function getProductList (pageNumber, pageSize) {
  let response;
  if (!(pageNumber && pageSize)) response = await get("/api/products/cart");
  else response = await get("/api/products/cart", {pageNumber, pageSize});

  if(response.status !== 200) {
    alert("데이터를 불러오지 못했습니다.");
  }

  return response.json();
}

/**
 * 장바구니 저장
 * @param {number} productBoardId 제품 게시판 id
 * @param {number} amount 구매 갯수
 * @returns {Promise<void>}
 */
export async function saveCart (productBoardId, amount) {
  const url = "/api/products/cart";
  const data = {
    productBoardId,
    amount
  }

  const response = await post(url, data);
  if(response.status !== 201) {
    alert("장바구니에 데이터를 저장하지 못했습니다.");
  }
}

/**
 * 장바구니 삭제
 * @param productBoardId
 * @returns {Promise<void>}
 */
export async function deleteCart (productBoardId) {
  const url = `/api/products/cart/${productBoardId}`;

  const response = await del(url);
  if(response.status !== 201) {
    alert("장바구니 데이터를 삭제하지 못했습니다.");
  }
}