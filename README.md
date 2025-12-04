# Population-Level Mask Use - Agent-Based Simulation  
CMPT 394 - Term Project (Fall 2025)  
University of Saskatchewan

## Overview
This project investigates the impact of population-level mask use on airborne infectious disease transmission using an Agent-Based Model (ABM).  
The model simulates:
- Disease transmission (SEIR progression)
- Mask adoption (initial, fear-driven, mandate-driven)
- Vaccination uptake and immunity waning
- Behavioral feedback through fear dynamics
- Network-based interactions
- Prevented exposures due to masking and vaccination


---

## Project Motivation
Masking has been widely used as a non-pharmaceutical intervention during outbreaks like COVID-19.  
Understanding how:
- mask effectiveness,
- mask adoption behavior,
- mandates,
- and vaccination

affect epidemic trajectories is important for informing policy.  
Agent-based models enable simulation of heterogeneous individuals, behavioral feedbacks, and contact-based transmission.

---

## Model Goals
This project aims to:

1. Evaluate the impact of **mask effectiveness** (30%, 60%, 95%) on epidemic outcomes.  
2. Study **behavior-driven mask adoption** influenced by fear of infection.  
3. Assess **vaccination timing and effectiveness** in combination with masking.  
4. Compare epidemic outcomes across multiple “what-if” scenarios.  
5. Perform **sensitivity analysis** on adoption rates and other parameters.

---

## Model Architecture

### Top-Level Agent: `Main`
- Initializes population and environment  
- Stores global parameters  
- Collects cumulative outcomes  
- Hosts time plots and reporters  
- Defines SEIR + vaccination outputs  

### Person Agent
Each agent has:
- Disease state (Susceptible, Exposed, Infective, Recovered, Dead)
- Mask state (masked/unmasked)
- Vaccination state
- Fear state (calm, afraid, panicked)
- Contact network links
- Functions for:
  - mask adoption  
  - vaccination uptake  
  - exposure computations  
  - immunity waning  
  - prevented exposure tracking  

### Network & Environment
- Distance-based network  
- 2D continuous space  
- Contacts occur based on proximity  
- Transmission and behavioral mechanics run on each step  

---

## Experiments Included

### 1. **Baseline Scenario**
Represents moderate real-world conditions:
- MaskEffectiveness = 0.66  
- InitialMaskAdoption = 0.2  
- No mandates  
- Vaccination available on day 40  

Baseline behavior:
- Epidemic peak around day 50  
- Moderate infections  
- Limited prevented exposures from masks  
- Fear-driven behavior stable (mostly calm)

---

### 2. **What-If Scenarios (Mask Effectiveness)**
#### Scenario A: MaskEffectiveness = 0.30
- Low-quality masks  
- Higher cumulative infections  
- Faster epidemic spread  
- Minimal prevented exposures  

#### Scenario B: MaskEffectiveness = 0.60
- Baseline reference  
- Moderate epidemic trajectory  

#### Scenario C: MaskEffectiveness = 0.95
- High-quality respirators  
- Strongly reduced infections  
- Flattened curve  
- Significant prevented exposures

These scenarios allow comparison of outcomes across different policy decisions.

---

## Sensitivity Analysis
A sensitivity experiment varies:
- **initialMaskAdoptionRate**  
or  
- **vaccineEffectiveness**  
or  
- **waningOfImmunity**

Key finding:
- Small changes in adoption rate produce large differences in total infections.
- Behavioral parameters (fear-driven adoption) amplify nonlinear effects.

---

## Running the Model

### Requirements
- AnyLogic 8.9.6 PLE  
- Java JDK installed (if required by AnyLogic)

### Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/UniversityOfSaskatchewan394858/term-project-fall-2025-population-level-mask-effectiveness
