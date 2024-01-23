export interface CartItem {
  productId: number;
  lenseConfig: {
    color: string;
    cylinder: string;
    degree: string;
    diopter: string;
    sphere: string;
  }
  quantity: number;
  sessionId: string;
}
