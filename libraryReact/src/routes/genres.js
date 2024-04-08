import React, { useState, useEffect } from 'react';
import { getGenres, deleteGenre, getGenre, addGenre, updateGenre } from "../api/genreService"
import { Link, useLoaderData, useNavigate } from "react-router-dom";

export async function loader({ params }) {
    const genre = await getGenre(params.genreId);
    return { genre };
}

export default function Genres() {
    const [genres, setGenres] = useState([]);
    const [reload, setReload] = useState(false);

    useEffect(() => {
        loadGenres()
    }, [reload]);

    return (
        <section >
            <div classNameName='table-title'>
                <h2>Жанры</h2>
                
                <table classNameName="table table-striped table-hover">   
                    <thead>
                        <tr>
                            <th>
                                Название
                            </th>
                            <th>
                                Описание
                            </th>
                            <th>
                                <Link to="add/" classNameName="btn btn-success">Добавить</Link>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        {genres.map(genre =>
                            <tr key={genre.id}>
                                <td>
                                    {genre.name}
                                </td>
                                <td>
                                    {genre.description}
                                </td>
                                <td>
                                    <Link to={`update/${genre.id}`} classNameName="btn btn-info">Изменить</Link>
                                    |
                                    <button 
                                        onClick={() => removeGenre(genre.id)} 
                                        value={genre.id}
                                        classNameName="btn btn-danger">
                                            Удалить
                                    </button>
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        </section>
    );

    async function loadGenres() {
        const genres = await getGenres()
        setGenres(genres)
    }

    async function removeGenre(id) {
        await deleteGenre(id)
        setReload(!reload)
    }
}

export function GenresAdd() {
    const navigate = useNavigate();

    const [name, setName] = useState('');
    const [description, setDescription] = useState('');

    return (
        <section className="form-container">
            <div className="form">
                <input
                    placeholder="Название"
                    className="form-control"
                    type="text"
                    onChange={(e) => setName(e.target.value)}
                />
                <textarea
                    placeholder="Описание"
                    className="form-control"
                    type="text"
                    onChange={(e) => setDescription(e.target.value)}
                />
                <button
                    onClick={addGenreClick}>
                    Добавить
                </button>
            </div>
        </section>
    );

    async function addGenreClick() {
        let genre = {}
        genre.id = crypto.randomUUID();
        genre.name = name
        genre.description = description
        await addGenre(genre)
        navigate('/genres')
    }
}

export function GenresUpdate() {
    const navigate = useNavigate();

    const { genre } = useLoaderData();
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');

    return (
        <section className="form-container">
            <div className="form">
                <input
                    placeholder="Название"
                    className="form-control"
                    type="text"
                    onChange={(e) => setName(e.target.value)}
                />
                <textarea
                    placeholder="Описание"
                    className="form-control"
                    type="text"
                    onChange={(e) => setDescription(e.target.value)}
                />
                <button
                    onClick={updateGenreClick}>
                    Изменить
                </button>
            </div>
        </section>
    );

    async function updateGenreClick() {
        genre.name = name
        genre.description = description
        await updateGenre(genre)
        navigate('/genres')
    }
}