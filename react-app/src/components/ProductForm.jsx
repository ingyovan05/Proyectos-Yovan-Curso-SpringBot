import { useEffect, useState } from "react";

const initialDataForm = {
  id: 0,
  name: "",
  description: "",
  price: "",
};
// eslint-disable-next-line react/prop-types
export const ProductForm = ({ productSelected, handlerAdd }) => {
  const [form, setForm] = useState(initialDataForm);

  const { id, name, description, price } = form;

  useEffect(() => {
    setForm(productSelected);
  }, [productSelected]);

  return (
    <form
      onSubmit={(event) => {
        event.preventDefault();

        if (!name || !description || !price) {
          alert("Debe de completar los datos del formulario!");
          return;
        }
        handlerAdd(form);
        setForm(initialDataForm);
      }}
    >
      <div>
        <input
          placeholder="Name"
          className="form-control my-3 w-75"
          style={{ marginBottom: "4px" }}
          name="name"
          value={name}
          onChange={(event) =>
            setForm({
              ...form,
              name: event.target.value,
            })
          }
        />
      </div>
      <div>
        <input
          placeholder="Description"
          className="form-control my-3 w-75"
          style={{ marginBottom: "4px" }}
          name="description"
          value={description}
          onChange={(event) =>
            setForm({
              ...form,
              description: event.target.value,
            })
          }
        />
      </div>
      <div>
        <input
          placeholder="Price"
          className="form-control my-3 w-75"
          style={{ marginBottom: "4px" }}
          name="price"
          value={price}
          onChange={(event) =>
            setForm({
              ...form,
              price: event.target.value,
            })
          }
        />
      </div>
      <div>
        <button type="submit" className="btn btn-primary">
          {id > 0 ? "Update" : "Create"}
        </button>
      </div>
    </form>
  );
};
