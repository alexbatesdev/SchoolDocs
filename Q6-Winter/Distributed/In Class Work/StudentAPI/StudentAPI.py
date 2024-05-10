# Initialization
from fastapi import FastAPI
from pydantic import BaseModel

app = FastAPI()

# CORS stuff?
# https://fastapi.tiangolo.com/tutorial/cors/
# Thanks copilot
# app.add_middleware(
#     CORSMiddleware, # Import this, from fastapi.middleware.cors?
#     allow_origins=["*"],
#     allow_credentials=True,
#     allow_methods=["*"],
#     allow_headers=["*"],
# )

# Models


class Student(BaseModel):
    name: str
    age: int
    GPA: float


# Data

students = [
    {"name": "Bruce Wayne", "age": 23, "GPA": 3.0},
    {"name": "Spud Dudnik", "age": 21, "GPA": 2.5},
    {"name": "Filly M. Cheesesteak", "age": 21, "GPA": 2.5},
]

# Methods


@app.get("/")
async def root():
    return {"message": "Hello World"}


@app.get("/students")
async def get_students():
    return students


@app.get("/students/{student_id}")
async def get_student(student_id):
    students[student_id]


@app.post("/students")
async def add_student(student: Student):
    students.append(student)
    return students
