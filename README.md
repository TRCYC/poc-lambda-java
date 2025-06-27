# poc-lambda-java

This repository provides a ready-to-use template for building AWS Lambda functions in Java. It includes robust environment management, configurable defaults, secure secrets handling, and a modern CI/CD pipeline. Use this repo as a starting point for new Lambda projects to ensure best practices and ease of development.

---

## 1. 📋 Getting Started: Creating a New Project from This Template

- Click **"Use this template"** on GitHub to create a new repository based on this one.
- **All files** will be copied, but repository variables and secrets must be manually recreated in your new repository settings. (GitHub does not copy secrets or variables automatically—be sure to set them up in the new repo.)
- After creation, review and update all repository variables and secrets for your environment.

---

## 2. 🛠️ Repository Variables for `dev` Environment

The following variables are defined in the GitHub repository settings and are referenced in the workflow for the `dev` environment. You can create other environments like qa and prod similarly.

| Name                   | Usage / Description                                    | Example Value                                 |
|------------------------|--------------------------------------------------------|-----------------------------------------------|
| `ECR_REPO`             | Full ECR repo URI for Docker image storage             | `1234567.dkr.ecr.us-east-1.amazonaws.com`     |
| `LAMBDA_FUNCTION_NAME` | AWS Lambda function name. Provide name of your LAMBDA. | `poc-lambda-java-dev`                         |
| `AWS_ROLE_ARN`         | IAM role for GitHub Actions to deploy AWS resources    | `arn:aws:iam::1234567:role/GitHubActionsRole` |
| `AWS_DEFAULT_REGION`   | AWS region for deployments                             | `us-east-1`                                   |
| `ENV`                  | Short name of the environment (dev, qa, prod, etc.)    | `dev`                                         |

**How to set:**  
Go to your repo → Settings → Variables and secrets → Actions → "Variables" tab. Create an env and define variables.

---

## 3. 🔒 Secrets and Additional Environment Variables

Secrets are used for sensitive data and must be set in your repository settings:

| Secret Name    | Description                           | Example Value         |
|----------------|---------------------------------------|----------------------|
| `PASSWORD`     | Example: DB or app password           | `super-secret-pw`    |
| *(add more as needed)* | *(your description)*           | *(value)*            |

**How to set:**  
Go to your repo → Settings → Variables and secrets → Actions → "Secrets" tab. Create an env and define secrets. You need to provide all the secrets as parameter to generate_env_json script in github workflow.

---

## 4. 📦 Java Package Structure & Naming

Package structure follows:

```
com.rcyc.<lob>.<domain>
```

- **`<lob>` (Line of Business):** Valid values: `onshore`, `onboard`, etc.
- **`<domain>`:** Sample values: `procurement`, `booking`, `hr`, etc.

**To rename packages:**  
- Use your IDE's refactor/rename tool to update `com.rcyc.<lob>.<domain>` to match your business area.
- This keeps code organized and clear for the project's context.

---

## 5. 🚦 Entry Points: `LambdaHandler` & `Env` Class

- **`LambdaHandler`**  
  The main entry point for AWS Lambda. Implements the `RequestHandler` interface. All Lambda invocations start here.

- **`Env`**  
  Utility class for all environment configuration.  
  - Always use `Env.get("VARIABLE_NAME")` to access environment variables, secrets, and defaults.
  - To add a new default, add it to the `DEFAULTS` map in `Env.java`. 
  - All variables defined in config.ENV.properties file will be available thru this class

Example usage:
```java
String functionName = Env.get("LAMBDA_FUNCTION_NAME");
String mySecret = Env.get("PASSWORD");
```

---

## 6. ⚙️ Configuration Property Files

- Configuration files like `config.dev.properties` are stored in the root of repo.
- These are used for non-secret, environment-specific settings.
- `logback.xml` loads logging properties from the environment-specific config file.

---

## 7. 📚 Dependency Management

- All dependencies are managed in `build.gradle`.
- When adding or updating dependencies:
  - Ensure compatibility with Java 21 (as set in the workflow).
  - Run `./gradlew clean build` to verify.
  - For AWS SDK or other cloud libraries, use stable versions.

---

## 8. 🏃‍♂️ Local Build & Run

**On Windows:**
```sh
./gradlew.bat clean build
```

**On MacOS/Linux:**
```sh
./gradlew clean build
```

- Run tests: `./gradlew test`
- JAR will be output to `build/libs/`

---

## 9. 🤖 CI/CD Workflow & Environment Propagation

- The workflow file `.github/workflows/lambda_build_dev_deploy.yml` automates build, Docker image creation, and deployment.
- All environment variables and secrets are passed to jobs as needed.
- The `generate_env_json.sh` script takes a `.properties` config and additional variables, and produces a JSON file of environment variables for Lambda.
- To add new secrets to deployments, update both your GitHub repo settings and reference them in the workflow and scripts as needed.

---

## 🔍 Quick Reference: Environment Variables in Code

The current `Env.java` default is:
```java
private static final Map<String, String> DEFAULTS = Map.of(
    "LAMBDA_FUNCTION", "dummy"
);
```
- To add more defaults, add entries to this map.
- Order of precedence: 1. Environment variable, 2. System property, 3. Default in code.

---

## 📝 Customization Steps

1. Set up repo variables and secrets in Settings.
2. Refactor Java package structure to fit your business (`com.rcyc.<lob>.<domain>`).
3. Update or add config property files as needed.
4. Update dependencies in `build.gradle` if required.
5. Adjust workflow YAML if your deployment or build process changes.

---

## 👋 Questions/Contact

For any issues or questions, contact the repository maintainer.

---

*For more details, see the source files and workflows in this repository. [Browse the repo](https://github.com/TRCYC/poc-lambda-java) for up-to-date info.*
