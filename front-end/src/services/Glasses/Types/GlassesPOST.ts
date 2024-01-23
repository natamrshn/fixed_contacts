import { Category } from "../../../Types/Category";
import { Variation } from "../../../Types/Variation";

export type GlassesPOSTRequest = {
  name: string;
  price: number;
  description: string;
  identifier: string;
  color: string;
  model: string;
  manufacturer: string;
  imageUrl: string;
  imageUrlSecond: string;
  coverImage: string;
  categories: Array<Category>;
};


export type GlassesPOSTResponse = {
  id: number;
  name: string;
  price: number;
  description: string;
  identifier: string;
  color: string;
  model: string;
  manufacturer: string;
  imageUrl: string;
  imageUrlSecond: string;
  categories: Array<Category>;
  variations: Array<Variation>;
}