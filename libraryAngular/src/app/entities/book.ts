import {Author} from './author'
import {Genre} from './genre'

export class Book {
    id!: string;
    title!: string;
    author!: Author;
    shortDescription!: string;
    cost!: number;
    downloadLink!: string;
    imageLink!: string
    genres: Genre[] = []
}