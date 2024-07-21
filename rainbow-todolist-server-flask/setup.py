from setuptools import setup, find_packages

setup(
    name='rainbow-todolist-server-flask',
    version='0.2',
    packages=find_packages(include=['rainbow_todolist_server']),
    package_dir={"": "src"},
    url='https://github.com/martlin2cz/rainbow-todolist',
    license='GNU GPL v3',
    author='martin2cz',
    author_email='',
    description='Rainbow-todolist flask server',

)
