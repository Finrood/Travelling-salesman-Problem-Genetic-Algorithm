# Traveling Salesman Problem Solver using Genetic Algorithm

![Java](https://img.shields.io/badge/Java-11%2B-blue)
![License](https://img.shields.io/badge/license-MIT-green)

This Java project implements a solution to the Traveling Salesman Problem (TSP) using a genetic algorithm. The Traveling Salesman Problem is a classic optimization problem where the goal is to find the shortest possible route that visits a set of cities and returns to the starting city.

## Algorithm Overview

The genetic algorithm works by evolving a population of potential solutions over a series of generations. Each solution is represented as a sequence of cities representing the order in which they are visited. The algorithm uses techniques inspired by natural evolution, such as selection, crossover, and mutation, to improve the quality of the solutions over time.

## Features

- Initialization of a random population of solutions.
- Fitness calculation to determine the quality of each solution.
- Tournament selection for parents to create the next generation.
- Crossover to combine parents' solutions and create offspring.
- Mutation to introduce small changes in the offspring.
- Elitism to preserve the best solutions across generations.
- Visualization of the best route found.
