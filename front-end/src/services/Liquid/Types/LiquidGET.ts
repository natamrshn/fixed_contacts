import { Category } from "../../../Types/Category";
import { Variation } from "../../../Types/Variation";

export interface LiquidGETRequest { 
  page: number;
  size: number;
  sort: Array<string>;
}

export interface LiquidGETResponse { 
  id: number;
  name: string;
  volume: number;
  price: number;
  description: string;
  identifier: string;
  imageUrl: string;
  imageUrlSecond: string;
  categories: Array<Category>;
  variations: Array<Variation>;
}
