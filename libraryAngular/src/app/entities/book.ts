import {Author} from './author'
import {Genre} from './genre'

export class Book {
    id: string = "";
    title: string = "";
    author: Author = new Author();
    shortDescription: string = "";
    cost: number = 0;
    downloadLink: string = "";
    imageLink: string = "";
    genres: Genre[] = []
}